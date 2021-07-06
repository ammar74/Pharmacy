package com.ammar.pharmacy.more;

import com.ammar.pharmacy.register.Coordinates;
import com.ammar.pharmacy.register.LocationAsCoordinates;

import java.util.Arrays;

public class PharmacyProfile {
    String name;
    String email;
    private Number[] phones;
    String locationAsAddress;
    LocationAsCoordinates locationAsCoordinates;
    double rate;
    Boolean verified;

    @Override
    public String toString() {
        return "PharmacyProfile{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + Arrays.toString(phones) +
                ", locationAsAddress='" + locationAsAddress + '\'' +
                ", locationAsCoordinates=" + locationAsCoordinates +
                ", rate=" + rate +
                ", verified=" + verified +
                '}';
    }

    public Number[] getPhones() {
        return phones;
    }

    public void setPhones(Number[] phones) {
        this.phones = phones;
    }

    public PharmacyProfile(String name, String email, Number[] phones, String locationAsAddress,
                           LocationAsCoordinates locationAsCoordinates, double rate, Boolean verified) {
        this.name = name;
        this.email = email;
        this.phones = phones;
        this.locationAsAddress = locationAsAddress;
        this.locationAsCoordinates = locationAsCoordinates;
        this.rate = rate;
        this.verified = verified;
    }
}
