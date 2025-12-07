package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.WatchProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchProductRepository extends JpaRepository<WatchProduct,Long> {

    List<WatchProduct> findTop6ByOrderByPriceAsc();
}
