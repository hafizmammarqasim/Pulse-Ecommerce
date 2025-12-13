package com.pulse.ecommerce.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_return")
public class OrderReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // order_id (FK → order.id)
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // ticket_id (FK → support_ticket.id)
    @OneToOne(optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    private SupportTicket supportTicket;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReturnStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Link to the items being returned
    @OneToMany(mappedBy = "orderReturn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderReturnItem> returnItems = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = ReturnStatus.REQUESTED;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public SupportTicket getSupportTicket() { return supportTicket; }
    public void setSupportTicket(SupportTicket supportTicket) { this.supportTicket = supportTicket; }

    public ReturnStatus getStatus() { return status; }
    public void setStatus(ReturnStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<OrderReturnItem> getReturnItems() { return returnItems; }
    public void setReturnItems(List<OrderReturnItem> returnItems) { this.returnItems = returnItems; }
}
