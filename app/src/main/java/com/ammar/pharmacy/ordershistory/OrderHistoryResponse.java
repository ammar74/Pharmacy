package com.ammar.pharmacy.ordershistory;

import com.ammar.pharmacy.currentorder.Order;

import java.util.List;

public  class OrderHistoryResponse{
        List<Order> orders;
        String message;

        public OrderHistoryResponse(List<Order> orders, String message) {
                this.orders = orders;
                this.message = message;
        }

        public List<Order> getOrders() {
                return orders;
        }

        public void setOrders(List<Order> orders) {
                this.orders = orders;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }
}
