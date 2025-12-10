package com.pulse.ecommerce.repository;


import com.pulse.ecommerce.model.Cart;
import com.pulse.ecommerce.model.CartItem;
import com.pulse.ecommerce.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
        Optional<CartItem> findByCartAndVariant(Cart cart, ProductVariant variant);
    }


