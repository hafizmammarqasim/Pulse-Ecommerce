package com.pulse.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "powerbank_products")
public class PowerBankProduct extends Product {

    private Integer capacityMah;
    private Integer maxOutputWatt;
    private String inputPort;
    private Boolean fastChargingSupport;
    private String batteryType;
    private Integer chargingTimeHours;
    private Integer weightGrams;
    private String dimensions;

    public Integer getCapacityMah() {
        return capacityMah;
    }

    public void setCapacityMah(Integer capacityMah) {
        this.capacityMah = capacityMah;
    }

    public Integer getMaxOutputWatt() {
        return maxOutputWatt;
    }

    public void setMaxOutputWatt(Integer maxOutputWatt) {
        this.maxOutputWatt = maxOutputWatt;
    }

    public String getInputPort() {
        return inputPort;
    }

    public void setInputPort(String inputPort) {
        this.inputPort = inputPort;
    }


    public Boolean getFastChargingSupport() {
        return fastChargingSupport;
    }

    public void setFastChargingSupport(Boolean fastChargingSupport) {
        this.fastChargingSupport = fastChargingSupport;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public Integer getChargingTimeHours() {
        return chargingTimeHours;
    }

    public void setChargingTimeHours(Integer chargingTimeHours) {
        this.chargingTimeHours = chargingTimeHours;
    }

    public Integer getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(Integer weightGrams) {
        this.weightGrams = weightGrams;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
}
