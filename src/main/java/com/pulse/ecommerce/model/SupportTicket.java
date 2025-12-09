package com.pulse.ecommerce.model;

import org.springframework.security.core.userdetails.User;

public class SupportTicket {
    private Long id;
    private User user;
    private Order order;
    private TicketType type;

}
