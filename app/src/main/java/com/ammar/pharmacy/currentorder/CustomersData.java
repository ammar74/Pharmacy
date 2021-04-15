package com.ammar.pharmacy.currentorder;

import com.ammar.pharmacy.register.LocationAsCoordinates;

public class CustomersData {
    String name;
    String _id;
    String phone;
    String locationAsAddress;
    LocationAsCoordinates locationAsCoordinates;

    public CustomersData(String name, String _id, String phone, String locationAsAddress,
                         LocationAsCoordinates locationAsCoordinates) {
        this.name = name;
        this._id = _id;
        this.phone = phone;
        this.locationAsAddress = locationAsAddress;
        this.locationAsCoordinates = locationAsCoordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocationAsAddress() {
        return locationAsAddress;
    }

    public void setLocationAsAddress(String locationAsAddress) {
        this.locationAsAddress = locationAsAddress;
    }

    public LocationAsCoordinates getLocationAsCoordinates() {
        return locationAsCoordinates;
    }

    public void setLocationAsCoordinates(LocationAsCoordinates locationAsCoordinates) {
        this.locationAsCoordinates = locationAsCoordinates;
    }

    @Override
    public String toString() {
        return "CustomersData{" +
                "name='" + name + '\'' +
                ", _id='" + _id + '\'' +
                ", phone='" + phone + '\'' +
                ", locationAsAddress='" + locationAsAddress + '\'' +
                ", locationAsCoordinates=" + locationAsCoordinates +
                '}';
    }
}
