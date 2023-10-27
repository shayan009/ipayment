package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerUpgradeRequest {

    @SerializedName("txn_amt")
    @Expose
    private String txnAmt;

    @SerializedName("txn_id")
    @Expose
    private String txnId;

    public CustomerUpgradeRequest(String txnAmt, String txnId) {
        this.txnAmt = txnAmt;
        this.txnId = txnId;
    }

    public String txnAmt() {
        return txnAmt;
    }

    public String txnId() {
        return txnId;
    }
}
