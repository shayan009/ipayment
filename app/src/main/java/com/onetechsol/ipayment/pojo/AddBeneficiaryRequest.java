package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddBeneficiaryRequest {

    @SerializedName("rem_mobile")
    @Expose
    private String remMobile;

    @SerializedName("bene_name")
    @Expose
    private String beneficiaryName;

    @SerializedName("bank")
    @Expose
    private String bank;

    @SerializedName("bene_ifsc")
    @Expose
    private String beneficiaryIfsc;

    @SerializedName("bene_account")
    @Expose
    private String beneAccount;


    public AddBeneficiaryRequest(String remMobile, String beneficiaryName, String bank, String beneficiaryIfsc, String beneAccount) {
        this.remMobile = remMobile;
        this.beneficiaryName = beneficiaryName;
        this.bank = bank;
        this.beneficiaryIfsc = beneficiaryIfsc;
        this.beneAccount = beneAccount;
    }

    public String remMobile() {
        return remMobile;
    }

    public String beneficiaryName() {
        return beneficiaryName;
    }

    public String bank() {
        return bank;
    }

    public String beneficiaryIfsc() {
        return beneficiaryIfsc;
    }

    public String beneAccount() {
        return beneAccount;
    }
}
