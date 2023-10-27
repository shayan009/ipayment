package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoneyTransferRequest {

    @SerializedName("rem_mobile")
    @Expose
    private String remMobile;

    @SerializedName("bene_name")
    @Expose
    private String benName;

    @SerializedName("bene_bank")
    @Expose
    private String benBank;

    @SerializedName("bene_ifsc")
    @Expose
    private String benIfsc;

    @SerializedName("bene_account")
    @Expose
    private String benAccount;

    @SerializedName("bene_id")
    @Expose
    private String benId;

    @SerializedName("amt")
    @Expose
    private String amt;

    @SerializedName("security_s")
    @Expose
    private String security;

    @SerializedName("tpin")
    @Expose
    private String tPin;

    @SerializedName("otp")
    @Expose
    private String otp;


    public MoneyTransferRequest(String remMobile, String benName, String benBank, String benIfsc, String benAccount, String benId, String amt, String security, String tPin, String otp) {
        this.remMobile = remMobile;
        this.benName = benName;
        this.benBank = benBank;
        this.benIfsc = benIfsc;
        this.benAccount = benAccount;
        this.benId = benId;
        this.amt = amt;
        this.security = security;
        this.tPin = tPin;
        this.otp = otp;
    }

    public String remMobile() {
        return remMobile;
    }

    public MoneyTransferRequest setRemMobile(String remMobile) {
        this.remMobile = remMobile;
        return this;
    }

    public String benName() {
        return benName;
    }

    public MoneyTransferRequest setBenName(String benName) {
        this.benName = benName;
        return this;
    }

    public String benBank() {
        return benBank;
    }

    public MoneyTransferRequest setBenBank(String benBank) {
        this.benBank = benBank;
        return this;
    }

    public String benIfsc() {
        return benIfsc;
    }

    public MoneyTransferRequest setBenIfsc(String benIfsc) {
        this.benIfsc = benIfsc;
        return this;
    }

    public String benAccount() {
        return benAccount;
    }

    public MoneyTransferRequest setBenAccount(String benAccount) {
        this.benAccount = benAccount;
        return this;
    }

    public String benId() {
        return benId;
    }

    public MoneyTransferRequest setBenId(String benId) {
        this.benId = benId;
        return this;
    }

    public String amt() {
        return amt;
    }

    public MoneyTransferRequest setAmt(String amt) {
        this.amt = amt;
        return this;
    }

    public String security() {
        return security;
    }

    public MoneyTransferRequest setSecurity(String security) {
        this.security = security;
        return this;
    }

    public String tPin() {
        return tPin;
    }

    public MoneyTransferRequest settPin(String tPin) {
        this.tPin = tPin;
        return this;
    }

    public String otp() {
        return otp;
    }

    public MoneyTransferRequest setOtp(String otp) {
        this.otp = otp;
        return this;
    }
}
