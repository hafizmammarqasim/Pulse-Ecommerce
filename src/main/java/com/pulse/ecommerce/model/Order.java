package com.pulse.ecommerce.model;



import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // customer_id (FK → user.id)
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private UserRecord customer;

    // delivery_id (FK → delivery.id)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED, RETURNED
    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // COD, CARD, EASYPAISA, ...
    @Column(name = "payment_method", nullable = false, length = 20)
    private String paymentMethod;

    // PENDING, PAID, REFUNDED, FAILED
    @Column(name = "payment_status", nullable = false, length = 20)
    private String paymentStatus;

    // STANDARD, EXPRESS, ...
    @Column(name = "shipping_method", length = 20)
    private String shippingMethod;

    @Column(name = "courier_name", length = 80)
    private String courierName;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    // getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UserRecord getCustomer() { return customer; }
    public void setCustomer(UserRecord customer) { this.customer = customer; }

    public Delivery getDelivery() { return delivery; }
    public void setDelivery(Delivery delivery) { this.delivery = delivery; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getShippingMethod() { return shippingMethod; }
    public void setShippingMethod(String shippingMethod) { this.shippingMethod = shippingMethod; }

    public String getCourierName() { return courierName; }
    public void setCourierName(String courierName) { this.courierName = courierName; }
}
