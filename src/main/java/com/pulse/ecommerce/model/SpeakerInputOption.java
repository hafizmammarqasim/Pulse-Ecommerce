package com.pulse.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "speaker_input_options")
public class SpeakerInputOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "speaker_product_id")
    private SpeakerProduct speakerProduct;

    // e.g. "Bluetooth", "AUX", "USB"
    @Column(nullable = false, length = 50)
    private String inputName;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpeakerProduct getSpeakerProduct() {
        return speakerProduct;
    }

    public void setSpeakerProduct(SpeakerProduct speakerProduct) {
        this.speakerProduct = speakerProduct;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }
}
