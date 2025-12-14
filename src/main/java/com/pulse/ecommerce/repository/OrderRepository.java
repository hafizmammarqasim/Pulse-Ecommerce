package com.pulse.ecommerce.repository;
import com.pulse.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;



import com.pulse.ecommerce.model.Order;
import com.pulse.ecommerce.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(UserRecord user);

    long countByStatus(String status);

    //For Super Admin
    @Query("SELECT SUM(o.totalAmount) FROM Order o")
    BigDecimal sumTotalAmount();
}
