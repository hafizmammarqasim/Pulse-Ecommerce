package com.pulse.ecommerce.service;

import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManagerService {

    private final ProductRepository productRepo;
    private final ProductVariantRepository variantRepo;
    private final CategoryRepository categoryRepo;

    public ProductManagerService(ProductRepository productRepo,
                                 ProductVariantRepository variantRepo,
                                 CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.variantRepo = variantRepo;
        this.categoryRepo = categoryRepo;
    }

    // list with optional filters
    public List<Product> listProducts(String categoryName, String keyword) {
        if (categoryName != null && !categoryName.isBlank()) {
            return productRepo.findByCategory_NameAndNameContainingIgnoreCase(categoryName,
                    keyword == null ? "" : keyword);
        }
        if (keyword != null && !keyword.isBlank()) {
            return productRepo.findByNameContainingIgnoreCase(keyword);
        }
        return productRepo.findAll();
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepo.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public List<ProductVariant> getVariantsForProduct(Long productId) {
        return variantRepo.findByProduct_Id(productId);
    }

    public ProductVariant saveVariant(ProductVariant variant) {
        return variantRepo.save(variant);
    }

    public void deleteVariant(Long id) {
        variantRepo.deleteById(id);
    }
}
