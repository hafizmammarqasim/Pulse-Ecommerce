package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
}
