package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayoutSubmitRequest {


    private String mode;

    private String amt;

    @SerializedName("acc_id")
    @Expose
    private String accountId;


    @SerializedName("security_s")
    @Expose
    private String security;

    private String tpin;

    private String otp;


    public PayoutSubmitRequest(String mode, String amt, String accountId, String security, String tpin, String otp) {
        this.mode = mode;
        this.amt = amt;
        this.accountId = accountId;
        this.security = security;
        this.tpin = tpin;
        this.otp = otp;
    }

    public String getMode() {
        return mode;
    }

    public String getAmt() {
        return amt;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getSecurity() {
        return security;
    }

    public String getTpin() {
        return tpin;
    }

    public String getOtp() {
        return otp;
    }
}
