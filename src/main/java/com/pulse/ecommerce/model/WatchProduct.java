package com.pulse.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "watch_products")
public class WatchProduct extends Product {

    private String displayType;
    private Double displaySizeInch;
    private String resolution;
    private Integer batteryCapacityMah;
    private Integer batteryLifeDays;
    private String waterResistanceRating;
    private Boolean heartRateSensor;
    private Boolean spo2Sensor;
    private Integer sportsModesCount;
    private Boolean gps;
    private String osCompatibility;
    private String strapMaterial;

    private Integer weightGrams;
    // getters/setters



    public Integer getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(Integer weightGrams) {
        this.weightGrams = weightGrams;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public Double getDisplaySizeInch() {
        return displaySizeInch;
    }

    public void setDisplaySizeInch(Double displaySizeInch) {
        this.displaySizeInch = displaySizeInch;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getBatteryCapacityMah() {
        return batteryCapacityMah;
    }

    public void setBatteryCapacityMah(Integer batteryCapacityMah) {
        this.batteryCapacityMah = batteryCapacityMah;
    }

    public Integer getBatteryLifeDays() {
        return batteryLifeDays;
    }

    public void setBatteryLifeDays(Integer batteryLifeDays) {
        this.batteryLifeDays = batteryLifeDays;
    }

    public String getWaterResistanceRating() {
        return waterResistanceRating;
    }

    public void setWaterResistanceRating(String waterResistanceRating) {
        this.waterResistanceRating = waterResistanceRating;
    }

    public Boolean getHeartRateSensor() {
        return heartRateSensor;
    }

    public void setHeartRateSensor(Boolean heartRateSensor) {
        this.heartRateSensor = heartRateSensor;
    }

    public Boolean getSpo2Sensor() {
        return spo2Sensor;
    }

    public void setSpo2Sensor(Boolean spo2Sensor) {
        this.spo2Sensor = spo2Sensor;
    }

    public Integer getSportsModesCount() {
        return sportsModesCount;
    }

    public void setSportsModesCount(Integer sportsModesCount) {
        this.sportsModesCount = sportsModesCount;
    }

    public Boolean getGps() {
        return gps;
    }

    public void setGps(Boolean gps) {
        this.gps = gps;
    }

    public String getOsCompatibility() {
        return osCompatibility;
    }

    public void setOsCompatibility(String osCompatibility) {
        this.osCompatibility = osCompatibility;
    }

    public String getStrapMaterial() {
        return strapMaterial;
    }

    public void setStrapMaterial(String strapMaterial) {
        this.strapMaterial = strapMaterial;
    }
}
