package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContaining(String name);
    boolean existsByCategory_Name(String name);

}

