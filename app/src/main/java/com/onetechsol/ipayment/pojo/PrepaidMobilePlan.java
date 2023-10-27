package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Transient;

import java.util.List;
import java.util.StringJoiner;

public class PrepaidMobilePlan {

    @SerializedName("rs")
    @Expose
    private String rs;

    @SerializedName("desc")
    @Expose
    private String desc;

    @SerializedName("validity")
    @Expose
    private String validity;

    @Transient
    private String data;


    @Transient
    private String details;

    @Transient
    private boolean validityPassed;

    @Transient
    private List<Benefits> benefitsList;

    public List<Benefits> benefitsList() {
        return benefitsList;
    }

    public PrepaidMobilePlan setRs(String rs) {
        this.rs = rs;
        return this;
    }

    public PrepaidMobilePlan setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public PrepaidMobilePlan setValidity(String validity) {
        this.validity = validity;
        return this;
    }

    public String details() {
        return details;
    }

    public PrepaidMobilePlan setDetails(String details) {
        this.details = details;
        return this;
    }

    public PrepaidMobilePlan setData(String data) {
        this.data = data;
        return this;
    }

    public PrepaidMobilePlan setValidityPassed(boolean validityPassed) {
        this.validityPassed = validityPassed;
        return this;
    }

    public String data() {
        return data;
    }

    public boolean validityPassed() {
        return validityPassed;
    }

    public PrepaidMobilePlan setBenefitsList(List<Benefits> benefitsList) {
        this.benefitsList = benefitsList;
        return this;
    }

    public String rs() {
        return rs;
    }

    public String desc() {
        return desc;
    }

    public String validity() {
        return validity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PrepaidMobilePlan.class.getSimpleName() + "[", "]")
                .add("rs='" + rs + "'")
                .add("desc='" + desc + "'")
                .add("validity='" + validity + "'")
                .add("data='" + data + "'")
                .add("details='" + details + "'")
                .add("validityPassed=" + validityPassed)
                .add("benefitsList=" + benefitsList)
                .toString();
    }
}
