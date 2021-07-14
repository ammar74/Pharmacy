package com.ammar.pharmacy.ordershistory;

import com.ammar.pharmacy.currentorder.CustomersData;
import com.ammar.pharmacy.currentorder.Order;

public class OrderDetailsResponse {
    Order orderData;
    CustomersData customersData;
    String message;

    @Override
    public String toString() {
        return "OrderDetailsResponse{" +
                "orderData=" + orderData +
                ", customersData=" + customersData +
                ", message='" + message + '\'' +
                '}';
    }

    public OrderDetailsResponse(Order orderData, CustomersData customersData, String message) {
        this.orderData = orderData;
        this.customersData = customersData;
        this.message = message;
    }

    public Order getOrderData() {
        return orderData;
    }

    public void setOrderData(Order orderData) {
        this.orderData = orderData;
    }

    public CustomersData getCustomersData() {
        return customersData;
    }

    public void setCustomersData(CustomersData customersData) {
        this.customersData = customersData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
