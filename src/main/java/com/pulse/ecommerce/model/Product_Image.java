package com.pulse.ecommerce.model;




import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class Product_Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // each image belongs to one product variant (e.g. Black, Blue)
    @ManyToOne(optional = false)
    @JoinColumn(name = "variant_id")
    private ProductVariant variant;




    // path or URL to the image file
    @Column(nullable = false, length = 255)
    private String imageUrl;




    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductVariant getVariant() {
        return variant;
    }

    public void setVariant(ProductVariant variant) {
        this.variant = variant;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}