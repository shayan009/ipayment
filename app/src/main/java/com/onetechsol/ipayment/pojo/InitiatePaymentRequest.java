package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitiatePaymentRequest {

    @SerializedName("txn_amt")
    @Expose
    private String txnAmt;


    public InitiatePaymentRequest(String txnAmt) {
        this.txnAmt = txnAmt;
    }
}
