package com.pulse.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "earbud_products")
public class EarBudProduct extends Product {

    private Integer driverSizeMm;
    private String frequencyResponse;
    private String bluetoothVersion;
    private Boolean mic;
    private String noiseCancellation; // NONE, ENC, ANC
    private Integer batteryLifeEarbudsHours;
    private Integer batteryLifeCaseHours;
    private Integer chargingTimeHours;
    private String chargingPort;
    private String waterResistanceRating;
    private Boolean lowLatencyMode;
    private Integer sizeMm;
    private Integer weightGrams;

    public Integer getSizeMm() {
        return sizeMm;
    }

    public void setSizeMm(Integer sizeMm) {
        this.sizeMm = sizeMm;
    }

    public Integer getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(Integer weightGrams) {
        this.weightGrams = weightGrams;
    }

    public Integer getDriverSizeMm() {
        return driverSizeMm;
    }

    public void setDriverSizeMm(Integer driverSizeMm) {
        this.driverSizeMm = driverSizeMm;
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


    public Boolean getMic() {
        return mic;
    }

    public void setMic(Boolean mic) {
        this.mic = mic;
    }

    public String getNoiseCancellation() {
        return noiseCancellation;
    }

    public void setNoiseCancellation(String noiseCancellation) {
        this.noiseCancellation = noiseCancellation;
    }

    public Integer getBatteryLifeEarbudsHours() {
        return batteryLifeEarbudsHours;
    }

    public void setBatteryLifeEarbudsHours(Integer batteryLifeEarbudsHours) {
        this.batteryLifeEarbudsHours = batteryLifeEarbudsHours;
    }

    public Integer getBatteryLifeCaseHours() {
        return batteryLifeCaseHours;
    }

    public void setBatteryLifeCaseHours(Integer batteryLifeCaseHours) {
        this.batteryLifeCaseHours = batteryLifeCaseHours;
    }

    public Integer getChargingTimeHours() {
        return chargingTimeHours;
    }

    public void setChargingTimeHours(Integer chargingTimeHours) {
        this.chargingTimeHours = chargingTimeHours;
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

    public Boolean getLowLatencyMode() {
        return lowLatencyMode;
    }

    public void setLowLatencyMode(Boolean lowLatencyMode) {
        this.lowLatencyMode = lowLatencyMode;
    }
}

