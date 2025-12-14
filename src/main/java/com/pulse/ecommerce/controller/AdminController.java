package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.Order;
import com.pulse.ecommerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Authentication authentication, Model model) {

        // This loop finds the highest-level role the user has
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            String role = auth.getAuthority();

            // SUPER ADMIN gets top priority
            if ("ROLE_SUPER_ADMIN".equals(role)) {
                // Fetch stats...
                 model.addAttribute("stats", adminService.getSuperAdminStats());
                return "admin/super-admin-dashboard";
            }

            // PRODUCT MANAGER
            if ("ROLE_PRODUCT_MANAGER".equals(role)) {
                // Fetch stats...
                 model.addAttribute("stats", adminService.getProductManagerStats());
                return "admin/product-manager-dashboard";
            }

            // ORDER MANAGER
            if ("ROLE_ORDER_MANAGER".equals(role)) {
                Map<String, Long> stats = adminService.getOrderManagerStats();
                model.addAttribute("stats", stats);

                List<Order> pendingOrders = adminService.getRecentPendingOrders();
                model.addAttribute("pendingOrders", pendingOrders);

                return "admin/order-manager-dashboard"; // Use the one with 3 tables
            }
        }

        // Fallback for generic "ADMIN" or if something goes wrong
        return "redirect:/";
    }
}