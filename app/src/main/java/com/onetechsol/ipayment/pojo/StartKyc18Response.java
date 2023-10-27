package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartKyc18Response extends Response {

    @SerializedName("txn_status")
    @Expose
    private String txnStatus;


    public String txnStatus() {
        return txnStatus;
    }

    public StartKyc18Response setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
        return this;
    }
}
