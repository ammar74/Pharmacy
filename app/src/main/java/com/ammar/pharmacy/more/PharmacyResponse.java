package com.ammar.pharmacy.more;

public class PharmacyResponse {
    String message;

    public PharmacyResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PharmacyResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
