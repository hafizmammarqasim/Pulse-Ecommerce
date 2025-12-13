package com.pulse.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "support_ticket")
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // customer_id (FK → user.id)
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private UserRecord customer;

    // order_id (FK → order.id)
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // order_return_id (FK → order_return.id, nullable)
    // This is nullable because a ticket might be just a "COMPLAINT" with no return involved.
    @OneToOne
    @JoinColumn(name = "order_return_id", nullable = true)
    private OrderReturn orderReturn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TicketType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TicketStatus status;

    @Column(nullable = false)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    // Auto-set timestamps
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
        if (status == null) status = TicketStatus.OPEN;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UserRecord getCustomer() { return customer; }
    public void setCustomer(UserRecord customer) { this.customer = customer; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public OrderReturn getOrderReturn() { return orderReturn; }
    public void setOrderReturn(OrderReturn orderReturn) { this.orderReturn = orderReturn; }

    public TicketType getType() { return type; }
    public void setType(TicketType type) { this.type = type; }

    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public LocalDate getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; }
}