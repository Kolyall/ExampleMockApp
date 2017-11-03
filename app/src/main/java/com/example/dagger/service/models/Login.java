package com.example.dagger.service.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Nick Unuchek on 03.11.2017.
 */

public class Login {
    @Expose String email;
    @Expose String password;

    public Login() {
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
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
}
