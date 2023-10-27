package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BuyInsuranceDetailRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public BuyInsuranceDetailRequest(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }

    public BuyInsuranceDetailRequest setVersion(String version) {
        this.version = version;
        return this;
    }
}
