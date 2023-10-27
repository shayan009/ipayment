package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetKitRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public GetKitRequest(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }

    public GetKitRequest setVersion(String version) {
        this.version = version;
        return this;
    }
}
