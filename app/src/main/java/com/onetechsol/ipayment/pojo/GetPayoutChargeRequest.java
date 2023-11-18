package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPayoutChargeRequest {


    private String amt;

    private String mode;

    public GetPayoutChargeRequest(String amt, String mode) {
        this.amt = amt;
        this.mode = mode;
    }

    public String getAmt() {
        return amt;
    }

    public String getMode() {
        return mode;
    }
}
