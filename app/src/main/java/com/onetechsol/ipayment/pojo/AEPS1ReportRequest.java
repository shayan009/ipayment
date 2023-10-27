package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AEPS1ReportRequest {

    @SerializedName("category_id")
    @Expose
    private String categoryId;

    @SerializedName("from_date")
    @Expose
    private String fromDate;


    @SerializedName("to_date")
    @Expose
    private String toDate;

    @SerializedName("txn_type")
    @Expose
    private String txnType;


    @SerializedName("txn_sub_type")
    @Expose
    private String txnSubType;


    @SerializedName("wallet_type")
    @Expose
    private String walletType;

    @SerializedName("txn_status")
    @Expose
    private String txnStatus;

    @SerializedName("page")
    @Expose
    private String page;


    public AEPS1ReportRequest(String categoryId, String fromDate, String toDate, String txnType, String txnSubType, String walletType, String txnStatus, String page) {
        this.categoryId = categoryId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.txnType = txnType;
        this.txnSubType = txnSubType;
        this.walletType = walletType;
        this.txnStatus = txnStatus;
        this.page = page;
    }

    public String categoryId() {
        return categoryId;
    }

    public String fromDate() {
        return fromDate;
    }

    public String toDate() {
        return toDate;
    }

    public String txnType() {
        return txnType;
    }

    public String txnSubType() {
        return txnSubType;
    }

    public String walletType() {
        return walletType;
    }

    public String txnStatus() {
        return txnStatus;
    }

    public String page() {
        return page;
    }
}
