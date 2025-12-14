package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.SupportTicket;
import com.pulse.ecommerce.model.TicketStatus;
import com.pulse.ecommerce.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/support")
public class AdminSupportController {

    @Autowired
    private SupportService supportService;

    // 1. Show the main ticket list with tabs
    @GetMapping
    public String showSupportList(@RequestParam(defaultValue = "OPEN") String status, Model model) {

        TicketStatus statusEnum = TicketStatus.valueOf(status.toUpperCase());
        List<SupportTicket> tickets = supportService.findTicketsByStatus(statusEnum);

        model.addAttribute("tickets", tickets);
        model.addAttribute("activeTab", status.toUpperCase());

        return "admin/admin-support-list";
    }

    // 2. Show the detail page for a single ticket
    @GetMapping("/view/{id}")
    public String viewTicket(@PathVariable Long id, Model model) {
        SupportTicket ticket = supportService.findTicketById(id);

        model.addAttribute("ticket", ticket);
        // Pass all possible status values to the dropdown
        model.addAttribute("allStatuses", TicketStatus.values());

        return "admin/admin-support-details";
    }

    // 3. Handle the form submission to update the status
    @PostMapping("/update-status")
    public String updateTicketStatus(@RequestParam Long ticketId, @RequestParam TicketStatus status) {
        supportService.updateTicketStatus(ticketId, status);

        // Go back to the detail page to see the change
        return "redirect:/admin/support";
    }
}