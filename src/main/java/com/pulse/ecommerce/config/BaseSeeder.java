package com.pulse.ecommerce.config;

import com.pulse.ecommerce.model.Product;
import com.pulse.ecommerce.model.ProductVariant;
import com.pulse.ecommerce.model.Product_Image;
import com.pulse.ecommerce.repository.ProductVariantRepository;
import com.pulse.ecommerce.repository.ProductImageRepository;

public abstract class BaseSeeder {

    protected final ProductVariantRepository variantRepo;
    protected final ProductImageRepository imageRepo;

    protected BaseSeeder(ProductVariantRepository variantRepo,
                         ProductImageRepository imageRepo) {
        this.variantRepo = variantRepo;
        this.imageRepo = imageRepo;
    }

    // now returns the saved variant
    protected ProductVariant createVariant(Product product,
                                           String color,
                                           String variantType,
                                           int stock) {
        ProductVariant v = new ProductVariant();
        v.setProduct(product);
        v.setColor(color);
        v.setVariantType(variantType);
        v.setStockQuantity(stock);
        return variantRepo.save(v);
    }

    // helper to add an image for a variant
    protected void addVariantImage(ProductVariant variant, String imageUrl) {
        Product_Image img = new Product_Image();
        img.setVariant(variant);
        img.setImageUrl(imageUrl);
        imageRepo.save(img);
    }
}
