package com.ammar.pharmacy.currentorder;

public class GetOrdersReturnBody {
    Order order;
    CustomersData customerData;
    String message;

    public GetOrdersReturnBody(Order order, CustomersData customerData, String message) {
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

    public CustomersData getCustomerData() {
        return customerData;
    }

    public void setCustomersData(CustomersData customersData) {
        this.customerData = customerData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GetOrdersReturnBody{" +
                "order=" + order +
                ", customersData=" + customerData +
                ", message='" + message + '\'' +
                '}';
    }
}
