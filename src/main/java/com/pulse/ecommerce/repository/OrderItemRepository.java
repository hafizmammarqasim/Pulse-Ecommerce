package com.pulse.ecommerce.repository;



import com.pulse.ecommerce.model.OrderItem;
import com.pulse.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order order);
}
