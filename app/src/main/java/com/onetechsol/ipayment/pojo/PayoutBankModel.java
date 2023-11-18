package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayoutBankModel {

    @SerializedName("account_holder")
    @Expose
    private String accountHolder;

    @SerializedName("account_no")
    @Expose
    private String accountNo;


    @SerializedName("branch_name")
    @Expose
    private String branchName;

    @SerializedName("bank_name")
    @Expose
    private String bankName;

    private String ifsc;

    @SerializedName("Mobile")
    @Expose
    private String mobile;

    @SerializedName("Email")
    @Expose
    private String email;

    private String id;

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBankName() {
        return bankName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PayoutBankModel{" +
                "accountHolder='" + accountHolder + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", branchName='" + branchName + '\'' +
                ", bankName='" + bankName + '\'' +
                ", ifsc='" + ifsc + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
