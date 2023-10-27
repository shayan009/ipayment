package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Transient;

public class BeneficiaryModel {


    @Transient
    boolean isVerified;
    @SerializedName("bene_id")
    @Expose
    private String benficiaryId;
    @SerializedName("bene_name")
    @Expose
    private String beneficiaryName;
    @SerializedName("bene_bank")
    @Expose
    private String beneficiaryBank;
    @SerializedName("bene_account")
    @Expose
    private String beneficiaryAccount;
    @SerializedName("bene_ifsc")
    @Expose
    private String beneIfsc;
    @SerializedName("bene_verify")
    @Expose
    private String beneVerify;

    public boolean isVerified() {
        return isVerified;
    }

    public String benficiaryId() {
        return benficiaryId;
    }

    public String beneficiaryName() {
        return beneficiaryName;
    }

    public String beneficiaryBank() {
        return beneficiaryBank;
    }

    public String beneficiaryAccount() {
        return beneficiaryAccount;
    }

    public String beneIfsc() {
        return beneIfsc;
    }

    public String beneVerify() {
        return beneVerify;
    }
}
