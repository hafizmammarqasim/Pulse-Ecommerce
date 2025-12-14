package com.pulse.ecommerce.config;



import com.pulse.ecommerce.model.Product;
import com.pulse.ecommerce.model.ProductVariant;
import com.pulse.ecommerce.repository.ProductVariantRepository;

public abstract class BaseSeeder {

    protected final ProductVariantRepository variantRepo;

    protected BaseSeeder(ProductVariantRepository variantRepo) {
        this.variantRepo = variantRepo;
    }

    protected void createVariant(Product product,
                                 String color,
                                 String variantType,
                                 int stock) {
        ProductVariant v = new ProductVariant();
        v.setProduct(product);
        v.setColor(color);
        v.setVariantType(variantType);
        v.setStockQuantity(stock);
        variantRepo.save(v);
    }
}
