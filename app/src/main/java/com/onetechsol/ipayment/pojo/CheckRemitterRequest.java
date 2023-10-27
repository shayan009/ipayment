package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CheckRemitterRequest {

    @SerializedName("rem_mobile")
    @Expose
    private String remMobile;

    public CheckRemitterRequest(String remMobile) {
        this.remMobile = remMobile;
    }

    public String remMobile() {
        return remMobile;
    }

    public CheckRemitterRequest setRemMobile(String remMobile) {
        this.remMobile = remMobile;
        return this;
    }
}
