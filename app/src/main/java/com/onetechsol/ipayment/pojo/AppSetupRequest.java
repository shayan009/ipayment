package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AppSetupRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public AppSetupRequest(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }

    public AppSetupRequest setVersion(String version) {
        this.version = version;
        return this;
    }

}
