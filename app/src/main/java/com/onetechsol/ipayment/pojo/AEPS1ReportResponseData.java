package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AEPS1ReportResponseData {


    @SerializedName("master_version")
    @Expose
    private int masterVersion;

    @SerializedName("company_version")
    @Expose
    private int companyVersion;

    @SerializedName("total_amt")
    @Expose
    private String totalAmt;


    @SerializedName("page_msg")
    @Expose
    private String pageMsg;

    @SerializedName("txn_list")
    @Expose
    private List<ReportItem> txnList;

    public String pageMsg() {
        return pageMsg;
    }

    public int masterVersion() {
        return masterVersion;
    }

    public int companyVersion() {
        return companyVersion;
    }

    public String totalAmt() {
        return totalAmt;
    }

    public List<ReportItem> txnList() {
        return txnList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AEPS1ReportResponseData{");
        sb.append("masterVersion=").append(masterVersion);
        sb.append(", companyVersion=").append(companyVersion);
        sb.append(", totalAmt='").append(totalAmt).append('\'');
        sb.append(", txnList=").append(txnList);
        sb.append('}');
        return sb.toString();
    }
}
