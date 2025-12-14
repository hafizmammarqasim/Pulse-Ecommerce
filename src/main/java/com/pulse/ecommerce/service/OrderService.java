package com.pulse.ecommerce.service;

import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.DeliveryRepository;
import com.pulse.ecommerce.repository.OrderRepository;
import com.pulse.ecommerce.repository.ProductVariantRepository;
import com.pulse.ecommerce.repository.SupportTicketRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final DeliveryRepository deliveryRepo;
    private final CartService cartService;
    private final ProductVariantRepository variantRepo;
    private final SupportTicketRepo ticketRepo;

    public OrderService(OrderRepository orderRepo,
                        DeliveryRepository deliveryRepo,
                        CartService cartService,
                        ProductVariantRepository variantRepo,
                        SupportTicketRepo ticketRepo) {
        this.orderRepo = orderRepo;
        this.deliveryRepo = deliveryRepo;
        this.cartService = cartService;
        this.variantRepo = variantRepo;
        this.ticketRepo = ticketRepo;
    }

    @Transactional
    public Order placeOrder(UserRecord user, Long deliveryId, String paymentMethod) {
        Cart cart = cartService.getActiveCart(user);
        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        Delivery delivery = deliveryRepo.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid delivery ID"));

        Order order = new Order();
        order.setCustomer(user);
        order.setDelivery(delivery);
        order.setPaymentMethod(paymentMethod);
        order.setPaymentStatus("PENDING");
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem ci : cart.getCartItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setVariant(ci.getVariant());
            oi.setQuantity(ci.getQuantity());

            BigDecimal unitPrice = ci.getVariant().getProduct().getPrice();
            oi.setUnitPrice(unitPrice);

            BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(ci.getQuantity()));
            oi.setTotal(lineTotal);

            order.getItems().add(oi);
            total = total.add(lineTotal);
        }

        order.setTotalAmount(total);

        Order saved = orderRepo.save(order);
        cartService.clearCart(cart);

        return saved;
    }

    public Optional<Order> getOrderById(Long id){
        return orderRepo.findById(id);
    }

    public List<Order> viewOrderHistory(UserRecord user){
        return orderRepo.findByCustomer(user);
    }

    @Transactional
    public void cancelOrder(Long orderId, String reason, UserRecord user) {

        // 1. Fetch Order
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        // 2. Security Check: Can only cancel PENDING orders
        if (!"PENDING".equalsIgnoreCase(order.getStatus())) {
            throw new IllegalStateException("Order cannot be canceled because it is already " + order.getStatus());
        }

        // 3. RESTORE STOCK (The Inventory Logic)
        for (OrderItem item : order.getItems()) {
            ProductVariant variant = item.getVariant();
            int currentStock = variant.getStockQuantity();
            int quantityToRestore = item.getQuantity();

            variant.setStockQuantity(currentStock + quantityToRestore);
            variantRepo.save(variant);
        }

        // 4. Update Order Status
        order.setStatus("CANCELLED");
        order.setPaymentStatus("REFUNDED"); // Assuming you refund immediately
        orderRepo.save(order);

        // 5. Create the Paper Trail (Ticket)
        // We set status to RESOLVED because the system auto-cancelled it.
        // The admin sees this in history but doesn't need to act.
        SupportTicket ticket = new SupportTicket();
        ticket.setCustomer(user);
        ticket.setOrder(order);
        ticket.setType(TicketType.CANCELLATION);
        ticket.setStatus(TicketStatus.RESOLVED);
        ticket.setSubject("Order #" + orderId + " Cancelled by User");
        ticket.setDescription("Reason: " + reason);
        ticket.setCreatedAt(java.time.LocalDate.now());

        ticketRepo.save(ticket);
    }

}
