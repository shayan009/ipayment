package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CheckSignupRequest {

    @SerializedName("mobile")
    @Expose
    public String mobile;

    public CheckSignupRequest(String mobile) {
        this.mobile = mobile;
    }

    public String version() {
        return mobile;
    }

    public String mobile() {
        return mobile;
    }

    public CheckSignupRequest setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
}
