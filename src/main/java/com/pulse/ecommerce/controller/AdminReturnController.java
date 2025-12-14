package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.OrderReturn;
import com.pulse.ecommerce.service.AdminReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/returns")
public class AdminReturnController {

    @Autowired private AdminReturnService returnService;

    // 1. Show the list of all pending returns
    @GetMapping
    public String listPendingReturns(Model model) {
        List<OrderReturn> returns = returnService.getPendingReturns();
        model.addAttribute("returns", returns);
        return "admin/admin-returns-list";
    }

    // 2. Show the detail page for a single return
    @GetMapping("/manage/{id}")
    public String showManageReturnPage(@PathVariable Long id, Model model) {
        OrderReturn orderReturn = returnService.getReturnById(id)
                .orElseThrow(() -> new RuntimeException("Return request not found"));

        model.addAttribute("returnRequest", orderReturn);
        return "admin/admin-return-details";
    }

    // 3. Handle Approve button click
    @PostMapping("/approve")
    public String approveReturn(@RequestParam Long returnId) {
        returnService.approveReturn(returnId);
        return "redirect:/admin/returns";
    }

    // 4. Handle Reject button click
    @PostMapping("/reject")
    public String rejectReturn(@RequestParam Long returnId) {
        returnService.rejectReturn(returnId);
        return "redirect:/admin/returns";
    }

    // 5. Handle Received button click
    @PostMapping("/receive")
    public String receiveReturn(@RequestParam Long returnId) {
        returnService.markAsReceivedAndRestock(returnId);
        return "redirect:/admin/returns";
    }

    // In AdminReturnController.java

    // ... (Your existing listPendingReturns method) ...

    @GetMapping("/approved")
    public String listApprovedReturns(Model model) {
        List<OrderReturn> returns = returnService.getApprovedReturns(); // We need to add this to the service
        model.addAttribute("returns", returns);
        return "admin/admin-returns-approved"; // A new HTML page
    }

    // In AdminReturnController.java

    @GetMapping("/history")
    public String showReturnHistory(Model model) {
        List<OrderReturn> returns = returnService.getCompletedReturns();
        model.addAttribute("returns", returns);
        return "admin/admin-returns-history"; // New HTML file
    }
}

