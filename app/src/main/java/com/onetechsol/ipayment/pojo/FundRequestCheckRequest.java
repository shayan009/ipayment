package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FundRequestCheckRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public FundRequestCheckRequest(String version) {
        this.version = version;
    }
}
