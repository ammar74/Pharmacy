package com.ammar.pharmacy.currentorder;

public class GetOrdersReturnBody {
    Order order;
    CustomerData customerData;
    String message;

    public GetOrdersReturnBody(Order order, CustomerData customerData, String message) {
        this.order = order;
        this.customerData = customerData;
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
