package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.PowerBankProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PowerBankRepository extends JpaRepository<PowerBankProduct,Long> {

    List<PowerBankProduct> findTop6ByOrderByPriceAsc();
}
