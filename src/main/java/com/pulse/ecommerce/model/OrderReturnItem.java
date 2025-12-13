package com.pulse.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_return_item")
public class OrderReturnItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // order_return_id (FK → order_return.id)
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_return_id", nullable = false)
    private OrderReturn orderReturn;

    // order_item_id (FK → order_item.id)
    // This links to the specific line item in the original order
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @Column(nullable = false)
    private int quantity;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OrderReturn getOrderReturn() { return orderReturn; }
    public void setOrderReturn(OrderReturn orderReturn) { this.orderReturn = orderReturn; }

    public OrderItem getOrderItem() { return orderItem; }
    public void setOrderItem(OrderItem orderItem) { this.orderItem = orderItem; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}