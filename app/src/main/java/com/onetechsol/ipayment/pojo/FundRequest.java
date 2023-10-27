package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FundRequest {

    @SerializedName("txn_amt")
    @Expose
    private String txnAmt;

    private String step;

    @SerializedName("request_to")
    @Expose
    private String requestTo;

    @SerializedName("bank_txn")
    @Expose
    private String bankTxn;

    @SerializedName("security_s")
    @Expose
    private String securitys;

    private String tpin;

    private String otp;

    public FundRequest(String txnAmt, String step, String requestTo, String bankTxn, String securitys, String tpin, String otp) {
        this.txnAmt = txnAmt;
        this.step = step;
        this.requestTo = requestTo;
        this.bankTxn = bankTxn;
        this.securitys = securitys;
        this.tpin = tpin;
        this.otp = otp;
    }

}
