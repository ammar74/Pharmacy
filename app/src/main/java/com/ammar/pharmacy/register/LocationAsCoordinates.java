package com.ammar.pharmacy.register;

public class LocationAsCoordinates {
    Coordinates coordinates ;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public LocationAsCoordinates(Coordinates coordinates){
        this.coordinates = coordinates ;
    }

    @Override
    public String toString() {
        return "LocationAsCoordinates{" +
                "coordinates=" + coordinates +
                '}';
    }
}
