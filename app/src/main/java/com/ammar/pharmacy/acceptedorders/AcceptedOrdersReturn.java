package com.ammar.pharmacy.acceptedorders;

import java.util.List;

public class AcceptedOrdersReturn {
    private List<OrderDetailsReturn> pharmacyOrders;
    private String message;

    public AcceptedOrdersReturn(List<OrderDetailsReturn> pharmacyOrders, String message) {
        this.setPharmacyOrders(pharmacyOrders);
        this.setMessage(message);
    }

    public List<OrderDetailsReturn> getPharmacyOrders() {
        return pharmacyOrders;
    }

    public void setPharmacyOrders(List<OrderDetailsReturn> pharmacyOrders) {
        this.pharmacyOrders = pharmacyOrders;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AcceptedOrdersReturn{" +
                "pharmacyOrders=" + getPharmacyOrders() +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
