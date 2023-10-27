package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CheckLoginRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public CheckLoginRequest(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }

    public CheckLoginRequest setVersion(String version) {
        this.version = version;
        return this;
    }
}
