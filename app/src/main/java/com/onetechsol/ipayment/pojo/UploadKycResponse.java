package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadKycResponse extends Response {

    @SerializedName("txn_status")
    @Expose
    private String txnStatus;

    @SerializedName("reload_url")
    @Expose
    private String reloadUrl;

    public String reloadUrl() {
        return reloadUrl;
    }

    public UploadKycResponse setReloadUrl(String reloadUrl) {
        this.reloadUrl = reloadUrl;
        return this;
    }

    public String txnStatus() {
        return txnStatus;
    }

    public UploadKycResponse setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
        return this;
    }
}
