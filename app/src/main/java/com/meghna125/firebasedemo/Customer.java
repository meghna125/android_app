package com.meghna125.firebasedemo;

import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;

/**
 * Created by HP on 17-08-2018.
 */

public class Customer {

    private String userName;
    private String email;
    private String password;
    private String Image;

    public Customer() {
    }

    public Customer(String userName, String email,String password,String Image) {
        this.Image=Image;
        this.userName=userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getImage() { return Image; }

    public void setImage(String Image) { this.Image = Image; }
}
