package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchBillRequest {


    @SerializedName("service_code")
    @Expose
    private String serviceCode;

    @SerializedName("operator")
    @Expose
    private String operator;

    @SerializedName("txn_number")
    @Expose
    private String txnNumber;

    @SerializedName("txn_number_name")
    @Expose
    private String txnNumberName;

    @SerializedName("txn_number_regex")
    @Expose
    private String txnNumberRegex;

    @SerializedName("txn_customer_no")
    @Expose
    private String txnCustomerNo;

    @SerializedName("txn_ad1")
    @Expose
    private String txnAd1;

    @SerializedName("txn_ad1_name")
    @Expose
    private String txnAd1Name;

    @SerializedName("txn_ad1_regex")
    @Expose
    private String txnAd1Regex;

    @SerializedName("txn_ad2")
    @Expose
    private String txnAd2;

    @SerializedName("txn_ad2_name")
    @Expose
    private String txnAd2Name;

    @SerializedName("txn_ad2_regex")
    @Expose
    private String txnAd2Regex;

    @SerializedName("txn_ad3")
    @Expose
    private String txnAd3;

    @SerializedName("txn_ad3_name")
    @Expose
    private String txnAd3Name;

    @SerializedName("txn_ad3_regex")
    @Expose
    private String txnAd3Regex;

    @SerializedName("txn_ad4")
    @Expose
    private String txnAd4;


    public FetchBillRequest(String serviceCode, String operator, String txnNumber, String txnNumberName, String txnNumberRegex, String txnCustomerNo, String txnAd1, String txnAd1Name, String txnAd1Regex, String txnAd2, String txnAd2Name, String txnAd2Regex, String txnAd3, String txnAd3Name, String txnAd3Regex, String txnAd4) {
        this.serviceCode = serviceCode;
        this.operator = operator;
        this.txnNumber = txnNumber;
        this.txnNumberName = txnNumberName;
        this.txnNumberRegex = txnNumberRegex;
        this.txnCustomerNo = txnCustomerNo;
        this.txnAd1 = txnAd1;
        this.txnAd1Name = txnAd1Name;
        this.txnAd1Regex = txnAd1Regex;
        this.txnAd2 = txnAd2;
        this.txnAd2Name = txnAd2Name;
        this.txnAd2Regex = txnAd2Regex;
        this.txnAd3 = txnAd3;
        this.txnAd3Name = txnAd3Name;
        this.txnAd3Regex = txnAd3Regex;
        this.txnAd4 = txnAd4;
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

    public String txnNumberName() {
        return txnNumberName;
    }

    public String txnNumberRegex() {
        return txnNumberRegex;
    }

    public String txnCustomerNo() {
        return txnCustomerNo;
    }

    public String txnAd1() {
        return txnAd1;
    }

    public String txnAd1Name() {
        return txnAd1Name;
    }

    public String txnAd1Regex() {
        return txnAd1Regex;
    }

    public String txnAd2() {
        return txnAd2;
    }

    public String txnAd2Name() {
        return txnAd2Name;
    }

    public String txnAd2Regex() {
        return txnAd2Regex;
    }

    public String txnAd3() {
        return txnAd3;
    }

    public String txnAd3Name() {
        return txnAd3Name;
    }

    public String txnAd3Regex() {
        return txnAd3Regex;
    }

    public String txnAd4() {
        return txnAd4;
    }
}
