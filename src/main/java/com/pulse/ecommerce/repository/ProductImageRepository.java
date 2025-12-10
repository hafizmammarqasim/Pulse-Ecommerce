package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.Product_Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<Product_Image, Long> {
}
