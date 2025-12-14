package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.Order;
import com.pulse.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String showAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/admin-orders-list"; // We will create this new HTML page
    }

    // 1. Show the management page (No Change)
    @GetMapping("/manage/{id}")
    public String showManageOrderPage(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        model.addAttribute("order", order);
        return "admin/admin-order-details";
    }

    // 2. Handle the update form submission (Simplified)
    @PostMapping("/update")
    public String updateOrder(
            @RequestParam Long orderId,
            @RequestParam String status,
            @RequestParam(required = false) String courierName
    ) {
        orderService.updateOrderStatusAndCourier(orderId, status, courierName);

        return "redirect:/admin/orders";
    }
}