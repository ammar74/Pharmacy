package com.ammar.pharmacy.currentorder;

public class GetOrdersReturnBody {
    Order order;
    CustomersData customersData;
    String message;

    public GetOrdersReturnBody(Order order, CustomersData customersData, String message) {
        this.order = order;
        this.customersData = customersData;
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
