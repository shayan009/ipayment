package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchBillResponse extends Response {


    @SerializedName("billnumber")
    @Expose
    private String billNumber;

    @SerializedName("customername")
    @Expose
    private String customerName;


    @SerializedName("amount_read")
    @Expose
    private String amountRead;

    @SerializedName("duedate")
    @Expose
    private String dueDate;

    @SerializedName("dueamount")
    @Expose
    private String dueAmount;

    @SerializedName("txn_fetch")
    @Expose
    private String txnFetch;

    @SerializedName("amount_status")
    @Expose
    private String amountStatus;

    @SerializedName("txn_fetch_dtl")
    @Expose
    private String txnFetchDtl;

    public String amountRead() {
        return amountRead;
    }


    public String txnFetchDtl() {
        return txnFetchDtl;
    }

    public String billNumber() {
        return billNumber;
    }

    public String customerName() {
        return customerName;
    }

    public String dueDate() {
        return dueDate;
    }

    public String dueAmount() {
        return dueAmount;
    }

    public String txnFetch() {
        return txnFetch;
    }

    public String amountStatus() {
        return amountStatus;
    }
}
