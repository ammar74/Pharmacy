package com.ammar.pharmacy.orders;

public class Order {

    String medcine;
    String date;
    String time;
    Boolean status=false ;

    public Order(String medcine, String date, String time, Boolean status) {
        this.medcine = medcine;
        this.date = date;
        this.time = time;
       this.status = status;
    }

    public String getMedcine() {
        return medcine;
    }

    public void setMedcine(String medcine) {
        this.medcine = medcine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        status = status;
    }
}
