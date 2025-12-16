package com.pulse.ecommerce.repository;



import com.pulse.ecommerce.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {


    long countByStockQuantityLessThan(int threshold);

    List<ProductVariant> findByStockQuantityLessThan(int threshold);
    List<ProductVariant> findByProduct_Id(Long productId);

}

