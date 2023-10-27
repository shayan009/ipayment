package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DMTChargeResponse extends Response {


    @SerializedName("charge")
    @Expose
    private String charge;

    @SerializedName("total_amt")
    @Expose
    private String totalAmt;

    public String charge() {
        return charge;
    }

    public String totalAmt() {
        return totalAmt;
    }
}
