package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnboardingCheckRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public OnboardingCheckRequest(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }

    public OnboardingCheckRequest setVersion(String version) {
        this.version = version;
        return this;
    }

}
