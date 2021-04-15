package com.ammar.pharmacy.acceptedorders;

import com.ammar.pharmacy.currentorder.CustomersData;
import com.ammar.pharmacy.currentorder.Order;

public class OrderDetailsReturn {
    Order orderdata;
    CustomersData customersData;

    public OrderDetailsReturn(Order order, CustomersData customersData) {
        this.orderdata = order;
        this.customersData = customersData;
    }

    public Order getOrder() {
        return orderdata;
    }

    public void setOrder(Order order) {
        this.orderdata = order;
    }

    public CustomersData getCustomersData() {
        return customersData;
    }

    public void setCustomersData(CustomersData customersData) {
        this.customersData = customersData;
    }

    @Override
    public String toString() {
        return "OrderDetailsReturn{" +
                "orderdata=" + orderdata +
                ", customerData=" + customersData +
                '}';
    }
}
