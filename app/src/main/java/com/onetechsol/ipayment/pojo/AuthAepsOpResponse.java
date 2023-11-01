package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthAepsOpResponse extends Response {

    @SerializedName("txn_status")
    @Expose
    private String txnStatus;


    AuthAepsOpResponseData data;


    public String getTxnStatus() {
        return txnStatus;
    }

    public AuthAepsOpResponseData getData() {
        return data;
    }
}
