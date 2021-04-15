package com.ammar.pharmacy.currentorder;

public class DoneOrderResponse {
    String msg;

    public DoneOrderResponse(String message) {
        this.msg = message;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }
}
