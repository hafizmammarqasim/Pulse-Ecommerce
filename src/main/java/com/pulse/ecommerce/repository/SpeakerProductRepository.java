package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.SpeakerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerProductRepository extends JpaRepository<SpeakerProduct,Long> {
    List<SpeakerProduct> findTop6ByOrderByPriceAsc();
}
