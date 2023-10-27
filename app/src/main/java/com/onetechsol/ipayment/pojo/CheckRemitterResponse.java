package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckRemitterResponse extends Response {

    @SerializedName("txn_status")
    @Expose
    private String txnStatus;

    @SerializedName("data")
    @Expose
    private CheckRemitterResponseData data;

    public String txnStatus() {
        return txnStatus;
    }

    public CheckRemitterResponseData data() {
        return data;
    }
}
