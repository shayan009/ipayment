package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FundTransferRequest {

    @SerializedName("service_wal")
    @Expose
    private String serviceWal;

    private String mode;

    private String amt;

    public FundTransferRequest(String serviceWal, String mode, String amt) {
        this.serviceWal = serviceWal;
        this.mode = mode;
        this.amt = amt;
    }

    public String serviceWal() {
        return serviceWal;
    }

    public String mode() {
        return mode;
    }

    public String amt() {
        return amt;
    }
}
