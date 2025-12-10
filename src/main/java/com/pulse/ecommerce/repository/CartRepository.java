package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.Cart;
import com.pulse.ecommerce.model.UserRecord;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomerAndStatus(UserRecord customer, String status);
}