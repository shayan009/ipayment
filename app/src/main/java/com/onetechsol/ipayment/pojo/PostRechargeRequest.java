package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostRechargeRequest {

    @SerializedName("circle")
    @Expose
    private String circle;

    @SerializedName("service_code")
    @Expose
    private String serviceCode;

    @SerializedName("operator")
    @Expose
    private String operator;

    @SerializedName("txn_number")
    @Expose
    private String txnNumber;

    @SerializedName("txn_customer_no")
    @Expose
    private String txnCustomerNo;

    @SerializedName("txn_ad1")
    @Expose
    private String txnAd1;

    @SerializedName("txn_ad2")
    @Expose
    private String txnAd2;

    @SerializedName("txn_ad3")
    @Expose
    private String txnAd3;

    @SerializedName("txn_ad4")
    @Expose
    private String txnAd4;

    @SerializedName("txn_amt")
    @Expose
    private String txnAmt;

    @SerializedName("txn_bill_no")
    @Expose
    private String txnBillNo;

    @SerializedName("txnCustomerName")
    @Expose
    private String txnCustomerName;

    @SerializedName("txn_fetch")
    @Expose
    private String txnFetch;

    @SerializedName("security_s")
    @Expose
    private String securityS;

    @SerializedName("tpin")
    @Expose
    private String tpin;

    @SerializedName("otp")
    @Expose
    private String otp;


    public PostRechargeRequest(String circle, String serviceCode, String operator, String txnNumber, String txnCustomerNo, String txnAd1, String txnAd2, String txnAd3, String txnAd4, String txnAmt, String txnBillNo, String txnCustomerName, String txnFetch, String securityS, String tpin, String otp) {
        this.circle = circle;
        this.serviceCode = serviceCode;
        this.operator = operator;
        this.txnNumber = txnNumber;
        this.txnCustomerNo = txnCustomerNo;
        this.txnAd1 = txnAd1;
        this.txnAd2 = txnAd2;
        this.txnAd3 = txnAd3;
        this.txnAd4 = txnAd4;
        this.txnAmt = txnAmt;
        this.txnBillNo = txnBillNo;
        this.txnCustomerName = txnCustomerName;
        this.txnFetch = txnFetch;
        this.securityS = securityS;
        this.tpin = tpin;
        this.otp = otp;
    }

    public String circle() {
        return circle;
    }

    public String serviceCode() {
        return serviceCode;
    }

    public String operator() {
        return operator;
    }

    public String txnNumber() {
        return txnNumber;
    }

    public String txnCustomerNo() {
        return txnCustomerNo;
    }

    public String txnAd1() {
        return txnAd1;
    }

    public String txnAd2() {
        return txnAd2;
    }

    public String txnAd3() {
        return txnAd3;
    }

    public String txnAd4() {
        return txnAd4;
    }

    public String txnAmt() {
        return txnAmt;
    }

    public String txnBillNo() {
        return txnBillNo;
    }

    public String txnCustomerName() {
        return txnCustomerName;
    }

    public String txnFetch() {
        return txnFetch;
    }

    public String securityS() {
        return securityS;
    }

    public String tpin() {
        return tpin;
    }

    public String otp() {
        return otp;
    }
}
