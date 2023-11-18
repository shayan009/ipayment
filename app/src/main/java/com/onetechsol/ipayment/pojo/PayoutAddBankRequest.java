package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayoutAddBankRequest {

    @SerializedName("acc_no")
    @Expose
    private String accountNo;

    @SerializedName("acc_holder")
    @Expose
    private String accountHolder;

    private String ifsc;

    private String branch;

    private String bank;

    @SerializedName("doc_up_id")
    @Expose
    private String docUploadId;

    @SerializedName("pan_up")
    @Expose
    private String panUp;

    @SerializedName("passbook_up")
    @Expose
    private String passbookUp;


    public PayoutAddBankRequest(String accountNo, String accountHolder, String ifsc, String branch, String bank, String docUploadId, String panUp, String passbookUp) {
        this.accountNo = accountNo;
        this.accountHolder = accountHolder;
        this.ifsc = ifsc;
        this.branch = branch;
        this.bank = bank;
        this.docUploadId = docUploadId;
        this.panUp = panUp;
        this.passbookUp = passbookUp;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getIfsc() {
        return ifsc;
    }

    public String getBranch() {
        return branch;
    }

    public String getBank() {
        return bank;
    }

    public String getDocUploadId() {
        return docUploadId;
    }

    public String getPanUp() {
        return panUp;
    }

    public String getPassbookUp() {
        return passbookUp;
    }
}
