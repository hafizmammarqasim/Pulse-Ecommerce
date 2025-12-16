package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
    @Query("""
           select oi.variant.product.id, sum(oi.quantity)
           from OrderItem oi
           where oi.order.status = 'DELIVERED'
             and oi.order.createdAt >= :since
           group by oi.variant.product.id
           """)
    List<Object[]> sumQtyByProductSince(@Param("since") LocalDateTime since);
}
