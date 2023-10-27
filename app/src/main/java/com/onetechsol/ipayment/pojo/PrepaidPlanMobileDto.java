package com.onetechsol.ipayment.pojo;

import java.util.List;
import java.util.StringJoiner;

public class PrepaidPlanMobileDto {


    private String id;
    private String amount;
    private String details;

    private String data;

    private String validity;
    private boolean isDataAvailable;

    private boolean validityPassed;

    private List<Benefits> benefitsList;

    public PrepaidPlanMobileDto() {
    }

    public PrepaidPlanMobileDto(String id, String amount, String details, String data, String validity, boolean isDataAvailable, boolean validityPassed, List<Benefits> benefitsList) {
        this.id = id;
        this.amount = amount;
        this.details = details;
        this.data = data;
        this.validity = validity;
        this.isDataAvailable = isDataAvailable;
        this.validityPassed = validityPassed;
        this.benefitsList = benefitsList;
    }

    public String validity() {
        return validity;
    }

    public PrepaidPlanMobileDto setValidity(String validity) {
        this.validity = validity;
        return this;
    }

    public String id() {
        return id;
    }

    public PrepaidPlanMobileDto setId(String id) {
        this.id = id;
        return this;
    }

    public PrepaidPlanMobileDto setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public PrepaidPlanMobileDto setDetails(String details) {
        this.details = details;
        return this;
    }

    public PrepaidPlanMobileDto setData(String data) {
        this.data = data;
        return this;
    }

    public boolean isDataAvailable() {
        return isDataAvailable;
    }

    public PrepaidPlanMobileDto setDataAvailable(boolean dataAvailable) {
        isDataAvailable = dataAvailable;
        return this;
    }

    public PrepaidPlanMobileDto setValidityPassed(boolean validityPassed) {
        this.validityPassed = validityPassed;
        return this;
    }

    public PrepaidPlanMobileDto setBenefitsList(List<Benefits> benefitsList) {
        this.benefitsList = benefitsList;
        return this;
    }

    public String amount() {
        return amount;
    }

    public String details() {
        return details;
    }

    public String data() {
        return data;
    }

    public boolean validityPassed() {
        return validityPassed;
    }

    public List<Benefits> benefitsList() {
        return benefitsList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PrepaidPlanMobileDto.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("amount='" + amount + "'")
                .add("details='" + details + "'")
                .add("data='" + data + "'")
                .add("validity='" + validity + "'")
                .add("isDataAvailable=" + isDataAvailable)
                .add("validityPassed=" + validityPassed)
                .add("benefitsList=" + benefitsList)
                .toString();
    }
}
