package com.ammar.pharmacy.login;

public class LoginObject {


    private String email;
    private String password;

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
    LoginObject(String email,String password){
        this.email=email;
        this.password=password;
    }
}
