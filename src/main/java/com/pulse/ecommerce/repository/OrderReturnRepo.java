package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.Order;
import com.pulse.ecommerce.model.OrderReturn;
import com.pulse.ecommerce.model.ReturnStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderReturnRepo extends JpaRepository<OrderReturn,Long> {

    OrderReturn findByOrder(Order order);

    boolean existsByOrderAndStatus(Order order, ReturnStatus returnStatus);

    long countByStatus(ReturnStatus status);

    List<OrderReturn> findByStatus(ReturnStatus status);

    List<OrderReturn> findByStatusNotIn(List<ReturnStatus> statuses);

}
