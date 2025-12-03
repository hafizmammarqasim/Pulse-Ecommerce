package com.pulse.ecommerce.model;


import jakarta.persistence.*;

@Entity
@Table(name = "earbud_codecs")
public class EarbudCodec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // owning earbud product
    @ManyToOne(optional = false)
    @JoinColumn(name = "earbud_product_id")
    private EarBudProduct earbudProduct;

    // e.g. "AAC", "SBC", "LDAC"
    @Column(nullable = false, length = 50)
    private String codecName;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EarBudProduct getEarbudProduct() {
        return earbudProduct;
    }

    public void setEarbudProduct(EarBudProduct earbudProduct) {
        this.earbudProduct = earbudProduct;
    }

    public String getCodecName() {
        return codecName;
    }

    public void setCodecName(String codecName) {
        this.codecName = codecName;
    }
}
