package com.pulse.ecommerce.service;

import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.DeliveryRepository;
import com.pulse.ecommerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final DeliveryRepository deliveryRepo;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepo,
                        DeliveryRepository deliveryRepo,
                        CartService cartService) {
        this.orderRepo = orderRepo;
        this.deliveryRepo = deliveryRepo;
        this.cartService = cartService;
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
}
