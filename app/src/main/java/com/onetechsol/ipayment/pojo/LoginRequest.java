package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public LoginRequest(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }

    public LoginRequest setVersion(String version) {
        this.version = version;
        return this;
    }
}
