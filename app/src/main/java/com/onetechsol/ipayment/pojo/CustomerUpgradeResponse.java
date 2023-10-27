package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerUpgradeResponse extends Response {

    @SerializedName("txn_status")
    @Expose
    private String txnStatus;

    @SerializedName("role_id")
    @Expose
    private String roleId;

    public String roleId() {
        return roleId;
    }

    public String txnStatus() {
        return txnStatus;
    }
}
