package com.ammar.pharmacy.register;

public class RegisterObject {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String  phones[];
    private String locationAsAddress;
    private LocationAsCoordinates locationAsCoordinates;

    public RegisterObject(String name, String email, String password, String confirmPassword,
                          String[] phones, String locationAsAddress, LocationAsCoordinates locationAsCoordinates) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phones = phones;
        this.locationAsAddress = locationAsAddress;
        this.locationAsCoordinates = locationAsCoordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
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
}
