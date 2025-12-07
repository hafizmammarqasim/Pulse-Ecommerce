package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.EarBudProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EarbudProductRepository extends JpaRepository<EarBudProduct,Long> {

    List<EarBudProduct> findTop6ByOrderByPriceAsc();
}
