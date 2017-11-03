package com.example.dagger.service.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Nick Unuchek on 03.11.2017.
 */

public class UserProfile {
    @Expose String authHash;
    @Expose String refreshToken;
    @Expose String id;
    @Expose String name;

    public UserProfile() {
    }

    public UserProfile(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthHash() {
        return authHash;
    }

    public void setAuthHash(String authHash) {
        this.authHash = authHash;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
