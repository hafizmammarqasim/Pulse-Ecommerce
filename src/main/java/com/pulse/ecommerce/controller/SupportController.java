package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.dto.ReturnRequestForm;
import com.pulse.ecommerce.model.SupportTicket;
import com.pulse.ecommerce.model.TicketType;
import com.pulse.ecommerce.model.UserRecord;
import com.pulse.ecommerce.service.AuthService;
import com.pulse.ecommerce.service.OrderService;
import com.pulse.ecommerce.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SupportController {
    OrderService orderService;
    AuthService authService;
    SupportService supportService;

    @Autowired
    public SupportController(AuthService authService, OrderService orderService, SupportService supportService){
        this.orderService = orderService;
        this.authService = authService;
        this.supportService = supportService;
    }

    // ==========================================
    // 1. VIEW TICKET HISTORY (The Missing Part)
    // ==========================================
    @GetMapping("/support/my-tickets")
    public String myTickets(Model model) {
        UserRecord user = authService.getCurrentUser();
        if(user == null) return "redirect:/login";

        List<SupportTicket> tickets = supportService.getTicketsForUser(user);
        model.addAttribute("tickets", tickets);

        return "my-tickets"; // You need to create this HTML
    }

    // ==========================================
    // 2. GENERAL CONTACT FORM (Inquiry/Complaint)
    // ==========================================
    @GetMapping("/support/create")
    public String showGeneralForm(Model model) {
        UserRecord user = authService.getCurrentUser();
        if(user == null) return "redirect:/login";

        model.addAttribute("ticket", new SupportTicket());
        return "support-create"; // The generic form HTML
    }

    @PostMapping("/support/save")
    public String submitGeneralTicket(@RequestParam String type,
                                      @RequestParam String subject,
                                      @RequestParam String description) {
        UserRecord user = authService.getCurrentUser();
        if(user == null) return "redirect:/login";

        supportService.createGeneralTicket(user, type, subject, description);
        return "redirect:/support/my-tickets";
    }

    @GetMapping("/return/initiate/{orderId}")
    public String initiateReturn(@PathVariable Long orderId, Model model){
        UserRecord user = authService.getCurrentUser();

        if(user == null){
            return "redirect:/login";
        }

        ReturnRequestForm form = supportService.makeRequestForm(orderId);
        model.addAttribute("returnForm", form );
        return "return-selection";
    }

    @PostMapping("/return/submit")
    public String submitReturn(@ModelAttribute ReturnRequestForm form){
        UserRecord user = authService.getCurrentUser();
        if(user == null){
            return "redirect:/login";
        }

        // Check if a return is already active (REQUESTED or APPROVED)
        boolean returnExists = supportService.returnAlreadyExists(form.getOrderId());

        if (returnExists) {
            throw new IllegalStateException("A return request is already active for this order.");
        }

        supportService.submitReturnRequest(form,user);
        return "return-selection-success";
    }

                        // ==========================================
                        //          CANCEL ORDER CONTROLLER         //
                        // ==========================================
    @GetMapping("/cancel/initiate/{orderId}")
    public String initiateCancel(@PathVariable Long orderId, Model model) {
        UserRecord user = authService.getCurrentUser();
        if(user == null) return "redirect:/login";

        // 1. Ticket Object with Defaults
        SupportTicket ticket = new SupportTicket();
        ticket.setType(TicketType.CANCELLATION); // Auto-select Type
        ticket.setSubject("Cancellation Request - Order #" + orderId); // Auto-fill Subject

        // 2. Flags for the HTML
        model.addAttribute("preOrderId", orderId);
        model.addAttribute("preType", "CANCELLATION");
        model.addAttribute("isLocked", true); // This triggers the "Read-Only" mode

        // 3. Passing the pre-filled ticket
        model.addAttribute("ticket", ticket);

        return "support-create";
    }

    @PostMapping("/cancel/submit")
    public String submitCancel(@RequestParam Long orderId,
                               @RequestParam String description,
                               Model model) {

        UserRecord user = authService.getCurrentUser();
        if(user == null) return "redirect:/login";

        try {
            // Call the service to do the heavy lifting
            orderService.cancelOrder(orderId, description, user);
            return "redirect:/order/view?success=cancelled";
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page";
        }
    }
}

