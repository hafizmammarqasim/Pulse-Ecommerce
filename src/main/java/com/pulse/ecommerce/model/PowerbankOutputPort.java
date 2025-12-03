package com.pulse.ecommerce.model;


import jakarta.persistence.*;

@Entity
@Table(name = "powerbank_output_ports")
public class PowerbankOutputPort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "powerbank_product_id")
    private PowerBankProduct powerbankProduct;

    // e.g. "USB-A", "USB-C"
    @Column(nullable = false, length = 50)
    private String portName;

    // how many of this port (optional but useful)
    private Integer portCount;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PowerBankProduct getPowerbankProduct() {
        return powerbankProduct;
    }

    public void setPowerbankProduct(PowerBankProduct powerbankProduct) {
        this.powerbankProduct = powerbankProduct;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public Integer getPortCount() {
        return portCount;
    }

    public void setPortCount(Integer portCount) {
        this.portCount = portCount;
    }
}
