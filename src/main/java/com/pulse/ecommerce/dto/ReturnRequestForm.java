package com.pulse.ecommerce.dto;

import java.util.List;

public class ReturnRequestForm {
    private Long orderId;
    private String reason;
    private List<ReturnItemDto> returnItems;

    public ReturnRequestForm() {
    }

    public ReturnRequestForm(Long orderId, String reason, List<ReturnItemDto> returnItems) {
        this.orderId = orderId;
        this.reason = reason;
        this.returnItems = returnItems;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ReturnItemDto> getReturnItems() {
        return returnItems;
    }

    public void setReturnItems(List<ReturnItemDto> returnItems) {
        this.returnItems = returnItems;
    }
}
