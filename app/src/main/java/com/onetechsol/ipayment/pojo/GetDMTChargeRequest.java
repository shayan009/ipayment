package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDMTChargeRequest {

    @SerializedName("amt")
    @Expose
    private String amt;

    public GetDMTChargeRequest(String amt) {
        this.amt = amt;
    }

    public String amt() {
        return amt;
    }

    public GetDMTChargeRequest setAmt(String amt) {
        this.amt = amt;
        return this;
    }
}
