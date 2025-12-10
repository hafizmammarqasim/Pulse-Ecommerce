package com.pulse.ecommerce.repository;
import com.pulse.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;



import com.pulse.ecommerce.model.Order;
import com.pulse.ecommerce.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // get all orders of a user, newest first
  //  List<Order> findByUser(UserRecord user)
}
