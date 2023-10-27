package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FundRequestCheckDetailRequest {

    @SerializedName("acc_id")
    @Expose
    private String accId;

    public FundRequestCheckDetailRequest(String accId) {
        this.accId = accId;
    }
}
