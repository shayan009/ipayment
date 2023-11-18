package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayoutCreateBankResponse extends Response {

    @SerializedName("txn_status")
    @Expose
    private String txnStatus;

    public String getTxnStatus() {
        return txnStatus;
    }
}
