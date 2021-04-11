package com.ammar.pharmacy.acceptedorders;

import com.ammar.pharmacy.currentorder.CustomerData;
import com.ammar.pharmacy.currentorder.Order;

public class AcceptedOrderReturn {
    Order order;
    CustomerData customerData;

    public AcceptedOrderReturn(Order order, CustomerData customerData) {
        this.order = order;
        this.customerData = customerData;
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
}
