package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckPayoutServiceRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public CheckPayoutServiceRequest(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
