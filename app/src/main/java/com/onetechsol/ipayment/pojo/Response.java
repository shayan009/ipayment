package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Response {

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("message")
    @Expose
    public String message;

    public String status() {
        return status;
    }

    public String message() {
        return message;
    }

    public Response setStatus(String status) {
        this.status = status;
        return this;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
