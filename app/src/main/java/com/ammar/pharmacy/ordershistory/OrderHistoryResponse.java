package com.ammar.pharmacy.ordershistory;

import java.util.List;

public  class OrderHistoryResponse{
        String message;
        List<PharmacyOrders> pharmacyOrders;


        public OrderHistoryResponse(String message,List<PharmacyOrders> pharmacyOrders) {
                this.pharmacyOrders = pharmacyOrders;
                this.message = message;
        }

        public List<PharmacyOrders> getOrders() { return pharmacyOrders; }

        public void setOrders(List<PharmacyOrders> pharmacyOrders) {
                this.pharmacyOrders = pharmacyOrders;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }
}
