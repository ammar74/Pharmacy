package com.ammar.pharmacy.more;

import com.ammar.pharmacy.register.Coordinates;
import com.ammar.pharmacy.register.LocationAsCoordinates;

import java.util.Arrays;

public class PharmacyProfile {
    String name;
    String email;
    private String[] phones;
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

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }
}
