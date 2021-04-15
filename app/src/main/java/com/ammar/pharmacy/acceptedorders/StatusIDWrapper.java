package com.ammar.pharmacy.acceptedorders;

public class StatusIDWrapper {
    String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public StatusIDWrapper(String orderId) {
        this.orderId = orderId;
    }
}
