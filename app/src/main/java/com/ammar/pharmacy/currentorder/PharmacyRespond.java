package com.ammar.pharmacy.currentorder;

public class PharmacyRespond {
    private String msg;

    public PharmacyRespond(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "PharmacyRespond{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
