package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceListRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public ServiceListRequest(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }

    public ServiceListRequest setVersion(String version) {
        this.version = version;
        return this;
    }

}
