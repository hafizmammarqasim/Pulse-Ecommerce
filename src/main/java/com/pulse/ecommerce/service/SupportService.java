package com.pulse.ecommerce.service;

import com.pulse.ecommerce.dto.ReturnItemDto;
import com.pulse.ecommerce.dto.ReturnRequestForm;
import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupportService {
   @Autowired SupportTicketRepo supportTicketRepo;
   @Autowired OrderRepository orderRepo;
   @Autowired OrderReturnRepo orderReturnRepo;
   @Autowired OrderReturnItemRepo orderReturnItemRepo;
   @Autowired OrderItemRepo orderItemRepo;

    // ... inside SupportService class ...

    // 1. GET USER TICKETS
    public List<SupportTicket> getTicketsForUser(UserRecord user) {
        return supportTicketRepo.findByCustomerOrderByCreatedAtDesc(user);
    }

    // 2. CREATE GENERAL TICKET (Complaint/Inquiry)
    // This handles tickets that DO NOT involve returning physical items
    public void createGeneralTicket(UserRecord user, String type, String subject, String description) {
        SupportTicket ticket = new SupportTicket();
        ticket.setCustomer(user);
        ticket.setType(TicketType.valueOf(type)); // COMPLAINT or INQUIRY
        ticket.setSubject(subject);
        ticket.setDescription(description);
        ticket.setStatus(TicketStatus.OPEN);

        // No Order or Return link needed for general queries
        supportTicketRepo.save(ticket);
    }

   public ReturnRequestForm makeRequestForm(Long orderId){

       if(orderId == null)
           return null;

       ReturnRequestForm requestForm = new ReturnRequestForm();
       requestForm.setOrderId(orderId);
       //Error: if you need to get it from repo, using orderId, use Order, not id
       //Otherwise get Order From order Repo and get the items from it
       // List<OrderItem> orderItems = orderItemRepo.findById(orderId)   ----Wrongggg-----
       //        .orElseThrow(()->new IllegalArgumentException("No order found against this Id"));

       Order order = orderRepo.findById(orderId).orElseThrow();
       // Temp items of dto to send data on the thymeleaf page
       List<ReturnItemDto> items = new ArrayList<>();
       for(OrderItem orderItem:order.getItems()){
           ReturnItemDto tempReturnItem = new ReturnItemDto();
           tempReturnItem.setOrderItemId(orderItem.getId());
           tempReturnItem.setMaxQuantity(orderItem.getQuantity());
           tempReturnItem.setProductName(orderItem.getVariant().getProduct().getName());
           tempReturnItem.setVariantType(orderItem.getVariant().getVariantType());
           tempReturnItem.setVariantColor(orderItem.getVariant().getColor());
           tempReturnItem.setReturnQuantity(1); // Default is one
           items.add(tempReturnItem);
       }

       requestForm.setReturnItems(items);
       return requestForm;
   }

   @Transactional //complete all tasks otherwise undo them
   public void submitReturnRequest(ReturnRequestForm form, UserRecord user) {

       // 1. Fetch the Original Order
       Order order = orderRepo.findById(form.getOrderId())
               .orElseThrow(() -> new IllegalArgumentException("Invalid Order ID"));

       // 2. Create the Support Ticket
       SupportTicket ticket = new SupportTicket();
       ticket.setCustomer(user);
       ticket.setOrder(order);
       ticket.setType(TicketType.RETURN); // Make sure your Enum has RETURN (or RETURN_REQUEST)
       ticket.setStatus(TicketStatus.OPEN);
       ticket.setSubject("Return Request for Order #" + order.getId());
       ticket.setDescription("User Reason: " + form.getReason());

       // Save Ticket first to get ID
       SupportTicket savedTicket = supportTicketRepo.save(ticket);

       // 3. Create the Order Return Header
       OrderReturn orderReturn = new OrderReturn();
       orderReturn.setOrder(order);
       orderReturn.setSupportTicket(savedTicket);
       orderReturn.setStatus(ReturnStatus.REQUESTED);

       OrderReturn savedReturn = orderReturnRepo.save(orderReturn);

       // 4. Process the Selected Items from DTO
       boolean hasItems = false;

       for (ReturnItemDto itemDto : form.getReturnItems()) {

           // Only process if checkbox was checked AND quantity > 0
           if (itemDto.isSelected() && itemDto.getReturnQuantity() > 0) {

               // Fetch the original OrderItem to link it
               OrderItem originalItem = orderItemRepo.findById(itemDto.getOrderItemId())
                       .orElseThrow(() -> new IllegalArgumentException("Invalid Order Item ID"));

               // Create the Return Line Item
               OrderReturnItem returnItem = new OrderReturnItem();
               returnItem.setOrderReturn(savedReturn); // Link to Header
               returnItem.setOrderItem(originalItem);  // Link to Original Purchase
               returnItem.setQuantity(itemDto.getReturnQuantity());

               orderReturnItemRepo.save(returnItem);
               hasItems = true;
           }
       }

       if (!hasItems) {
           throw new IllegalArgumentException("No items selected for return");
       }

       // Optional: Update ticket with return link if you kept that column
       // savedTicket.setOrderReturn(savedReturn);
       // supportTicketRepo.save(savedTicket);
   }

   public boolean returnAlreadyExists(Long orderId){
       Order order = orderRepo.findById(orderId).orElseThrow();

      if(orderReturnRepo.existsByOrderAndStatus(order, ReturnStatus.REQUESTED))
          return true;

       return orderReturnRepo.existsByOrderAndStatus(order, ReturnStatus.APPROVED);
   }


}
