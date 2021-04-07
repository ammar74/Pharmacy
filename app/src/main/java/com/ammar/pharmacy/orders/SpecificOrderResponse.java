package com.ammar.pharmacy.orders;

public class SpecificOrderResponse{

    Order orderData;
    String message;
    CustomerData customerData;

    public SpecificOrderResponse(Order orderData, String message, CustomerData customerData) {
        this.orderData = orderData;
        this.message = message;
        this.customerData = customerData;
    }

    public Order getOrderData() {
        return orderData;
    }

    public void setOrderData(Order orderData) {
        this.orderData = orderData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }
}

