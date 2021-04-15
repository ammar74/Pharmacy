package com.ammar.pharmacy.ordershistory;

import java.util.List;

public class PharmacyOrders {

    String _id;
    String customerID;
    String date;
    String globalStatus;
    String orderByPhoto;
    String orderByTexting;
    String rate;
    String report;
    List<PharmaciesID> pharmaciesID;

    public List<PharmaciesID> getPharmaciesID() {
        return pharmaciesID;
    }

    public void setPharmaciesID(List<PharmaciesID> pharmaciesID) {
        this.pharmaciesID = pharmaciesID;
    }

    public PharmacyOrders(String _id, String customerID, String date, String globalStatus,
                          String orderByPhoto, String orderByTexting, String rate, String report,
                          List<PharmaciesID> pharmaciesID) {
        this._id = _id;
        this.customerID = customerID;
        this.date = date;
        this.globalStatus = globalStatus;
        this.orderByPhoto = orderByPhoto;
        this.orderByTexting = orderByTexting;
        this.rate = rate;
        this.report = report;
        this.pharmaciesID = pharmaciesID;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGlobalStatus() {
        return globalStatus;
    }

    public void setGlobalStatus(String globalStatus) {
        this.globalStatus = globalStatus;
    }

    public String getOrderByPhoto() {
        return orderByPhoto;
    }

    public void setOrderByPhoto(String orderByPhoto) {
        this.orderByPhoto = orderByPhoto;
    }

    public String getOrderByTexting() {
        return orderByTexting;
    }

    public void setOrderByTexting(String orderByTexting) {
        this.orderByTexting = orderByTexting;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "PharmacyOrders{" +
                "_id='" + _id + '\'' +
                ", customerID='" + customerID + '\'' +
                ", date='" + date + '\'' +
                ", globalStatus='" + globalStatus + '\'' +
                ", orderByPhoto='" + orderByPhoto + '\'' +
                ", orderByTexting='" + orderByTexting + '\'' +
                ", rate='" + rate + '\'' +
                ", report='" + report + '\'' +
                ", pharmaciesID=" + pharmaciesID +
                '}';
    }
}
