package com.first.smartstudent;

public class UserDetails {
  private String name, email, mobile, password, confirmPassword;
  private byte[] image;
  private int id;

    public UserDetails(String name, String email, String mobile, String password, String confirmPassword, byte[] image, int id) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public byte[] getImage() {
        return image;
    }

    public int getId() {
        return id;
    }
}
