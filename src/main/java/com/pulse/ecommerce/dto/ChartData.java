package com.pulse.ecommerce.dto;

// A generic DTO for sending chart/report data to the frontend
public class ChartData {
    private String label;
    private Object value;

    // Constructors
    public ChartData() {}

    public ChartData(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    // Getters and Setters
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public Object getValue() { return value; }
    public void setValue(Object value) { this.value = value; }
}