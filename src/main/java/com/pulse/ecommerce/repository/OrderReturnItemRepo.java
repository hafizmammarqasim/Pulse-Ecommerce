package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.OrderReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReturnItemRepo extends JpaRepository<OrderReturnItem,Long> {
}
