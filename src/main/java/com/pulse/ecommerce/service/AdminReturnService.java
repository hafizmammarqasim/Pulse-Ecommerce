package com.pulse.ecommerce.service;

import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.OrderReturnRepo;
import com.pulse.ecommerce.repository.ProductVariantRepository;
import com.pulse.ecommerce.repository.SupportTicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AdminReturnService {

    @Autowired private OrderReturnRepo returnRepo;
    @Autowired private SupportTicketRepo ticketRepo;
    @Autowired private ProductVariantRepository variantRepo; // For restock

    // 1. Get all pending returns for the dashboard
    public List<OrderReturn> getPendingReturns() {
        return returnRepo.findByStatus(ReturnStatus.REQUESTED);
    }

    // 2. Get a single return by ID for the detail page
    public Optional<OrderReturn> getReturnById(Long id) {
        return returnRepo.findById(id);
    }

    // 3. Approve a return request
    @Transactional
    public void approveReturn(Long returnId) {
        OrderReturn orderReturn = returnRepo.findById(returnId)
                .orElseThrow(() -> new IllegalArgumentException("Return not found"));

        // Update statuses
        orderReturn.setStatus(ReturnStatus.APPROVED);

        SupportTicket ticket = orderReturn.getSupportTicket();
        ticket.setStatus(TicketStatus.IN_PROGRESS);

        returnRepo.save(orderReturn);
        ticketRepo.save(ticket);
    }

    // 4. Reject a return request
    @Transactional
    public void rejectReturn(Long returnId) {
        OrderReturn orderReturn = returnRepo.findById(returnId)
                .orElseThrow(() -> new IllegalArgumentException("Return not found"));

        // Update statuses
        orderReturn.setStatus(ReturnStatus.REJECTED);

        SupportTicket ticket = orderReturn.getSupportTicket();
        ticket.setStatus(TicketStatus.REJECTED);

        returnRepo.save(orderReturn);
        ticketRepo.save(ticket);
    }

    // 5. Mark as Received and Restock
    @Transactional
    public void markAsReceivedAndRestock(Long returnId) {
        OrderReturn orderReturn = returnRepo.findById(returnId)
                .orElseThrow(() -> new IllegalArgumentException("Return not found"));

        // Update status
        orderReturn.setStatus(ReturnStatus.RECEIVED);

        // Add stock back to inventory
        for (OrderReturnItem item : orderReturn.getReturnItems()) {
            ProductVariant variant = item.getOrderItem().getVariant();
            variant.setStockQuantity(variant.getStockQuantity() + item.getQuantity());
            variantRepo.save(variant);
        }

        returnRepo.save(orderReturn);
    }

    // Method to get all returns that are waiting for the customer to ship back
    public List<OrderReturn> getApprovedReturns() {
        return returnRepo.findByStatus(ReturnStatus.APPROVED);
    }

    // Method to get all returns that are finished
    public List<OrderReturn> getCompletedReturns() {
        // We fetch all returns that are NOT pending or approved
        return returnRepo.findByStatusNotIn(List.of(ReturnStatus.REQUESTED, ReturnStatus.APPROVED));
    }
}
