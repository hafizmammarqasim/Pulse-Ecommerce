package com.pulse.ecommerce.model;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
@Entity
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private TicketType type;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private String subject;
    private String description;
    private LocalDate createdAt;

}
