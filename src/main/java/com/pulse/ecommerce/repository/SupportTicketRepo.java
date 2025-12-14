package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportTicketRepo extends JpaRepository<SupportTicket,Long> {

    Optional<SupportTicket> findByOrderAndCustomer(Order order, UserRecord user);

    List <SupportTicket> findByCustomerOrderByCreatedAtDesc(UserRecord customer);


    long countByStatus(TicketStatus status);

    // Finds tickets that are NOT a specific type (e.g., show me everything BUT returns)
    List<SupportTicket> findByTypeNot(TicketType type);

    // Finds tickets by a specific status
    List<SupportTicket> findByStatus(TicketStatus status);

    // Finds tickets by a specific type
    List<SupportTicket> findByType(TicketType type);

    List<SupportTicket> findByTypeNotAndStatus(TicketType type, TicketStatus status);

    List<SupportTicket> findByStatusOrderByCreatedAtDesc(TicketStatus status);

}

