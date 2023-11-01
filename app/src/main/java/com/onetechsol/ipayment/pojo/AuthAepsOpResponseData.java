package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthAepsOpResponseData {

    @SerializedName("withdrawalamount")
    @Expose
    private String withdrawalAmount;


    @SerializedName("txn")
    @Expose
    private String transactionNo;

    private String mobileNumber;

    @SerializedName("balanceamount")
    @Expose
    private String balanceAmount;


    @SerializedName("txn_date")
    @Expose
    private String txnDate;


    public String getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public String getTxnDate() {
        return txnDate;
    }
}
