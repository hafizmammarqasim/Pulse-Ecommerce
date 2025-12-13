package com.pulse.ecommerce.dto;

import com.pulse.ecommerce.model.ProductVariant;

public class ReturnItemDto {
    private Long orderItemId;
    private String productName;
    private String variantType;
    private String variantColor;
    private int maxQuantity;

    private boolean selected;
    private int returnQuantity;

    public ReturnItemDto(Long orderItemId, String variantType, String variantColor, String productName, int maxQuantity, int returnQuantity, boolean selected) {
        this.orderItemId = orderItemId;
        this.variantType = variantType;
        this.variantColor = variantColor;
        this.productName = productName;
        this.maxQuantity = maxQuantity;
        this.returnQuantity = returnQuantity;
        this.selected = selected;
    }

    public ReturnItemDto() {
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVariantType() {
        return variantType;
    }

    public void setVariantType(String variant) {
        this.variantType = variant;
    }

    public String getVariantColor() {
        return variantColor;
    }

    public void setVariantColor(String variantColor) {
        this.variantColor = variantColor;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(int returnQuantity) {
        this.returnQuantity = returnQuantity;
    }
}
