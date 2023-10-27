package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AffiliateProductModel {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("sell_earn_id")
    @Expose
    private String sellEarnId;

    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("is_active")
    @Expose
    private boolean isActive;

    @SerializedName("Benefits")
    @Expose
    private List<BenefitModel> benefitModels;

    @SerializedName("Goals")
    @Expose
    private List<GoalModel> goalModels;

    @SerializedName("opening_charge")
    @Expose
    private double joinFee;

    @SerializedName("annual_fee")
    @Expose
    private double annualFee;

    @SerializedName("approval_rate")
    @Expose
    private String approvalRate;

    @SerializedName("earning")
    @Expose
    private double maxEarn;


    @SerializedName("rate_of_interest")
    @Expose
    private String interest;


    @SerializedName("image")
    private String iconUrl;

    @SerializedName("short_name")
    @Expose
    private String shortName;

    @SerializedName("is_instant_upi_available")
    private String isInstantUpiAvailable;

    @SerializedName("is_vdc_available")
    private String isVdcAvailable;

    @SerializedName("min_account_bal")
    @Expose
    private String minAccountBalance;

    @SerializedName("creation_time")
    private String accountCreationTime;

    @SerializedName("type")
    @Expose
    private String type;


    public String link() {
        return link;
    }

    public String id() {
        return id;
    }

    public String sellEarnId() {
        return sellEarnId;
    }

    public String name() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<BenefitModel> benefitModels() {
        return benefitModels;
    }

    public List<GoalModel> goalModels() {
        return goalModels;
    }

    public double joinFee() {
        return joinFee;
    }

    public double annualFee() {
        return annualFee;
    }

    public String approvalRate() {
        return approvalRate;
    }

    public double maxEarn() {
        return maxEarn;
    }

    public String interest() {
        return interest;
    }

    public String iconUrl() {
        return iconUrl;
    }

    public String shortName() {
        return shortName;
    }

    public String isInstantUpiAvailable() {
        return isInstantUpiAvailable;
    }

    public String isVdcAvailable() {
        return isVdcAvailable;
    }

    public String minAccountBalance() {
        return minAccountBalance;
    }

    public String accountCreationTime() {
        return accountCreationTime;
    }

    public String type() {
        return type;
    }
}
