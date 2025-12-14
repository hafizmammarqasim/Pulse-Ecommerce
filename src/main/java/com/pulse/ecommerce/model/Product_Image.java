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

    // 2. For Earbud Codecs (e.g., AAC, SBC)
    @ManyToOne
    @JoinColumn(name = "earbud_codec_id")
    private EarbudCodec earbudCodec;

    // 3. For Speaker Input Options (e.g., AUX, Bluetooth)
    @ManyToOne
    @JoinColumn(name = "speaker_input_id")
    private SpeakerInputOption speakerInputOption;

    // 4. For Powerbank Ports (e.g., USB-A, Type-C)
    @ManyToOne
    @JoinColumn(name = "powerbank_port_id")
    private PowerbankOutputPort powerbankPort;


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
    public EarbudCodec getEarbudCodec() {
        return earbudCodec;
    }

    public void setEarbudCodec(EarbudCodec earbudCodec) {
        this.earbudCodec = earbudCodec;
    }

    public SpeakerInputOption getSpeakerInputOption() {
        return speakerInputOption;
    }

    public void setSpeakerInputOption(SpeakerInputOption speakerInputOption) {
        this.speakerInputOption = speakerInputOption;
    }

    public PowerbankOutputPort getPowerbankPort() {
        return powerbankPort;
    }

    public void setPowerbankPort(PowerbankOutputPort powerbankPort) {
        this.powerbankPort = powerbankPort;
    }
}