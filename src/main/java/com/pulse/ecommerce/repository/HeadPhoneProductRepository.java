package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.HeadPhoneProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HeadPhoneProductRepository extends JpaRepository<HeadPhoneProduct,Long> {

    List<HeadPhoneProduct> findTop6ByOrderByPriceAsc();
}
