package com.pulse.ecommerce.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "headphone_products")
public class HeadPhoneProduct extends Product {

    private Integer driverSizeMm;
    private String frequencyResponse;
    private Integer impedanceOhm;
    private String bluetoothVersion;
    private Boolean wireless;
    private String noiseCancellation; // NONE, ANC
    private Boolean mic;
    private Integer batteryLifeHours;
    private String chargingPort;
    private String audioInput;
    private Integer weightGrams;
    private Integer sizeMm;

    public Integer getSizeMm() {
        return sizeMm;
    }

    public void setSizeMm(Integer sizeMm) {
        this.sizeMm = sizeMm;
    }
    // getters/setters

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

    public Integer getImpedanceOhm() {
        return impedanceOhm;
    }

    public void setImpedanceOhm(Integer impedanceOhm) {
        this.impedanceOhm = impedanceOhm;
    }

    public String getBluetoothVersion() {
        return bluetoothVersion;
    }

    public void setBluetoothVersion(String bluetoothVersion) {
        this.bluetoothVersion = bluetoothVersion;
    }

    public Boolean getWireless() {
        return wireless;
    }

    public void setWireless(Boolean wireless) {
        this.wireless = wireless;
    }

    public String getNoiseCancellation() {
        return noiseCancellation;
    }

    public void setNoiseCancellation(String noiseCancellation) {
        this.noiseCancellation = noiseCancellation;
    }

    public Boolean getMic() {
        return mic;
    }

    public void setMic(Boolean mic) {
        this.mic = mic;
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

    public String getAudioInput() {
        return audioInput;
    }

    public void setAudioInput(String audioInput) {
        this.audioInput = audioInput;
    }

    public Integer getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(Integer weightGrams) {
        this.weightGrams = weightGrams;
    }
}
