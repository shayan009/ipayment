package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobilePrepaidPlansRequest {

    @SerializedName("service_code")
    @Expose
    private String serviceCode;


    @SerializedName("operator")
    @Expose
    private String operator;


    @SerializedName("circle")
    @Expose
    private String circle;


    @SerializedName("plan")
    @Expose
    private String plan;

    @SerializedName("txn_number")
    @Expose
    private String txnNumber;


    public MobilePrepaidPlansRequest(String serviceCode, String operator, String circle, String plan, String txnNumber) {
        this.serviceCode = serviceCode;
        this.operator = operator;
        this.circle = circle;
        this.plan = plan;
        this.txnNumber = txnNumber;
    }

    public String serviceCode() {
        return serviceCode;
    }

    public String operator() {
        return operator;
    }

    public String circle() {
        return circle;
    }

    public String plan() {
        return plan;
    }

    public String txnNumber() {
        return txnNumber;
    }
}
