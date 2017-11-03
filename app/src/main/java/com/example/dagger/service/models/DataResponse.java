package com.example.dagger.service.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Nick Unuchek on 03.11.2017.
 */

public class DataResponse<T> {
    @Expose private Status status;
    @Expose private T data;

    public enum Status{
        success,failed;
    }

    public DataResponse() {
    }

    public DataResponse(Status status, T data) {
        this.status = status;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
