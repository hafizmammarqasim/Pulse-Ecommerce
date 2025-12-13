package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.Order;
import com.pulse.ecommerce.model.SupportTicket;
import com.pulse.ecommerce.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportTicketRepo extends JpaRepository<SupportTicket,Long> {

    Optional<SupportTicket> findByOrderAndCustomer(Order order, UserRecord user);

    List <SupportTicket> findByCustomerOrderByCreatedAtDesc(UserRecord customer);

}

