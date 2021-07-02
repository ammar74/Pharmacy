package com.ammar.pharmacy.more;

import com.ammar.pharmacy.register.Coordinates;

public class PharmacyProfile {
    String name;
    String email;
    private String[] phones;
    String locationAsAddress;
    Coordinates locationAsCoordinates;
    double rate;
    Boolean verified;

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }
}
