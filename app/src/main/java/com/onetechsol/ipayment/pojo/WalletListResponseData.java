package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WalletListResponseData {


    @SerializedName("master_version")
    @Expose
    private int masterVersion;

    @SerializedName("company_version")
    @Expose
    private int companyVersion;

    @SerializedName("retl_status")
    @Expose
    private String retailerStatus;

    @SerializedName("fund_status")
    @Expose
    private String fundStatus;

    @SerializedName("total_walet")
    @Expose
    private String totalWallet;

    @SerializedName("learner_amt")
    @Expose
    private String learnerAmt;

    @SerializedName("walet_list")
    @Expose
    private List<WalletList> waletList;


    public WalletListResponseData() {
    }

    public String totalWallet() {
        return totalWallet;
    }

    public String learnerAmt() {
        return learnerAmt;
    }

    public int masterVersion() {
        return masterVersion;
    }

    public int companyVersion() {
        return companyVersion;
    }


    public String retailerStatus() {
        return retailerStatus;
    }

    public WalletListResponseData setRetailerStatus(String retailerStatus) {
        this.retailerStatus = retailerStatus;
        return this;
    }

    public String fundStatus() {
        return fundStatus;
    }

    public WalletListResponseData setFundStatus(String fundStatus) {
        this.fundStatus = fundStatus;
        return this;
    }

    public String totalWalet() {
        return totalWallet;
    }

    public WalletListResponseData setTotalWalet(String totalWalet) {
        this.totalWallet = totalWalet;
        return this;
    }

    public List<WalletList> waletList() {
        return waletList;
    }

    public WalletListResponseData setWaletList(List<WalletList> waletList) {
        this.waletList = waletList;
        return this;
    }

    public WalletListResponseData setMasterVersion(int masterVersion) {
        this.masterVersion = masterVersion;
        return this;
    }

    public WalletListResponseData setCompanyVersion(int companyVersion) {
        this.companyVersion = companyVersion;
        return this;
    }

}
