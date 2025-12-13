
package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.DeliveryRepository;
import com.pulse.ecommerce.repository.OrderReturnRepo;
import com.pulse.ecommerce.service.AuthService;
import com.pulse.ecommerce.service.CartService;
import com.pulse.ecommerce.service.OrderService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/order")
public class OrderController {

    private final AuthService authService;
    private final CartService cartService;
    private final DeliveryRepository deliveryRepo;
    private final OrderService orderService;
    private final OrderReturnRepo orderReturnRepo;

    public OrderController(AuthService authService,
                           CartService cartService,
                           DeliveryRepository deliveryRepo,
                           OrderService orderService,
                           OrderReturnRepo orderReturnRepo) {
        this.authService = authService;
        this.cartService = cartService;
        this.deliveryRepo = deliveryRepo;
        this.orderService = orderService;
        this.orderReturnRepo = orderReturnRepo;
    }

    // ... existing /delivery GET + POST

    // Step 3: checkout review page
    @GetMapping("/checkout")
    public String showCheckout(@RequestParam Long deliveryId,
                               Model model) {
        UserRecord user = authService.getCurrentUser();
        if (user == null) return "redirect:/login";

        Cart cart = cartService.getActiveCart(user);
        if (cart == null || cart.getCartItems().isEmpty()) {
            return "redirect:/cart/view";
        }

        Delivery delivery = deliveryRepo.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid delivery ID"));

        model.addAttribute("cart", cart);
        model.addAttribute("delivery", delivery);
        // default payment method COD for now
        model.addAttribute("paymentMethod", "COD");
        return "checkout";
    }

    // Step 4: place order
    @PostMapping("/place")
    public String placeOrder(@RequestParam Long deliveryId,
                             @RequestParam String paymentMethod,
                             Model model) {
        UserRecord user = authService.getCurrentUser();
        if (user == null) return "redirect:/login";

        Order order = orderService.placeOrder(user, deliveryId, paymentMethod);
        model.addAttribute("order", order);
        return "otder-success"; // simple thank-you page
    }
    // STEP 1: show delivery form
    @GetMapping("/delivery")
    public String showDeliveryForm(Model model) {
        System.out.println("HIT /order/delivery GET");
        UserRecord user = authService.getCurrentUser();
        if (user == null) return "redirect:/login";

        Cart cart = cartService.getActiveCart(user);
        if (cart == null || cart.getCartItems().isEmpty()) {
            return "redirect:/cart/view";
        }

        model.addAttribute("delivery", new Delivery());
        model.addAttribute("cart", cart);
        model.addAttribute("savedDeliveries", deliveryRepo.findByUser(user));
        return "delivery"; // your delivery HTML
    }

    // STEP 2: handle delivery form submit
    @PostMapping("/delivery")
    public String handleDelivery(@ModelAttribute Delivery delivery) {
        System.out.println("HIT /order/delivery POST");
        UserRecord user = authService.getCurrentUser();
        if (user == null) return "redirect:/login";

        delivery.setUser(user);
        Delivery saved = deliveryRepo.save(delivery);

        // go to checkout with deliveryId
        return "redirect:/order/checkout?deliveryId=" + saved.getId();
    }

    @GetMapping("/view")
    public String orderHistory(Model model) {
        UserRecord user = authService.getCurrentUser();
        List<Order> orderList = orderService.viewOrderHistory(user);

        if(user == null)
            return "redirect:/login";
        // We create a Map: OrderID -> ReturnStatus
        Map<Long, String> returnStatuses = new HashMap<>();

        for (Order order : orderList) {
            // We have to ask the Return Repo: "Do you have a return for this order?"
            OrderReturn ret = orderReturnRepo.findByOrder(order);
            if (ret != null) {
                returnStatuses.put(order.getId(), ret.getStatus().name());
            }
        }

        model.addAttribute("orderList", orderList);
        model.addAttribute("returnStatuses", returnStatuses); // Send the map
        return "order-history";
    }


    @GetMapping("/details/{id}")
    public String viewSingleOrder(@PathVariable Long id, Model model) {

        UserRecord user = authService.getCurrentUser();
        if(user == null){
            return "redirect:/login";
        }

        Order order = orderService.getOrderById(id)
                .orElseThrow(()-> new RuntimeException("No order found against this id"));

        // Since we didn't link it in the Entity, we fetch it manually here
        OrderReturn activeReturn = orderReturnRepo.findByOrder(order);

        // Send it to the HTML
        model.addAttribute("activeReturn", activeReturn);

        model.addAttribute("order", order);
        return "order-details";
    }

}
