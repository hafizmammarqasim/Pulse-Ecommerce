package com.pulse.ecommerce.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "speaker_products")
public class SpeakerProduct extends Product {

    private Integer outputPowerWatt;
    private String frequencyResponse;
    private String bluetoothVersion;
    private Integer batteryLifeHours;
    private String chargingPort;
    private String waterResistanceRating;
    private Boolean stereoPairingSupport;

    public Integer getSizeMm() {
        return sizeMm;
    }

    public void setSizeMm(Integer sizeMm) {
        this.sizeMm = sizeMm;
    }

    public Integer getDriverSizeMm() {
        return driverSizeMm;
    }

    public void setDriverSizeMm(Integer driverSizeMm) {
        this.driverSizeMm = driverSizeMm;
    }


    private Integer weightGrams;
    private Integer sizeMm;
    private Integer driverSizeMm;


    // getters/setters

    public Integer getOutputPowerWatt() {
        return outputPowerWatt;
    }

    public void setOutputPowerWatt(Integer outputPowerWatt) {
        this.outputPowerWatt = outputPowerWatt;
    }

    public String getFrequencyResponse() {
        return frequencyResponse;
    }

    public void setFrequencyResponse(String frequencyResponse) {
        this.frequencyResponse = frequencyResponse;
    }

    public String getBluetoothVersion() {
        return bluetoothVersion;
    }

    public void setBluetoothVersion(String bluetoothVersion) {
        this.bluetoothVersion = bluetoothVersion;
    }

    public Integer getBatteryLifeHours() {
        return batteryLifeHours;
    }

    public void setBatteryLifeHours(Integer batteryLifeHours) {
        this.batteryLifeHours = batteryLifeHours;
    }

    public String getChargingPort() {
        return chargingPort;
    }

    public void setChargingPort(String chargingPort) {
        this.chargingPort = chargingPort;
    }

    public String getWaterResistanceRating() {
        return waterResistanceRating;
    }

    public void setWaterResistanceRating(String waterResistanceRating) {
        this.waterResistanceRating = waterResistanceRating;
    }

    public Boolean getStereoPairingSupport() {
        return stereoPairingSupport;
    }

    public void setStereoPairingSupport(Boolean stereoPairingSupport) {
        this.stereoPairingSupport = stereoPairingSupport;
    }


    public Integer getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(Integer weightGrams) {
        this.weightGrams = weightGrams;
    }
}

