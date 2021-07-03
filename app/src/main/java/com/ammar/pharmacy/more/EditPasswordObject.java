package com.ammar.pharmacy.more;

public class EditPasswordObject {
    private String oldPassword;
    private String Password;
    private String confirmPassword;

    public EditPasswordObject(String oldPassword, String password, String confirmPassword) {
        this.oldPassword = oldPassword;
        Password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
