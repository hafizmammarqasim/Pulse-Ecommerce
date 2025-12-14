package com.pulse.ecommerce.repository;



import com.pulse.ecommerce.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    long countByStockQuantityLessThan(int stockLevel);

}

