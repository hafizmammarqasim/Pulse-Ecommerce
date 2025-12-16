package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.Product;
import com.pulse.ecommerce.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByNameContaining(String name);
    boolean existsByCategory_Name(String name);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategory_NameAndNameContainingIgnoreCase(String categoryName, String name);

    @Modifying
    @Query("update Product p set p.trending = false")
    void resetTrending();

    @Modifying
    @Query("update Product p set p.bestSeller = false")
    void resetBestSeller();
    @Query("""
      select p from Product p
      left join fetch p.variants v
      left join fetch v.images
      where p.id = :id
    """)
    Optional<Product> findWithVariantsAndImagesById(@Param("id") Long id);
}

