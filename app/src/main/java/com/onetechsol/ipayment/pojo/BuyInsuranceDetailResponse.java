package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuyInsuranceDetailResponse extends Response {


    @SerializedName("txn_status")
    @Expose
    private String txnStatus;

    @SerializedName("reload_url")
    @Expose
    private String reloadUrl;

    public String txnStatus() {
        return txnStatus;
    }

    public String reloadUrl() {
        return reloadUrl;
    }
}
