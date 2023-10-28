package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.StringJoiner;

public class MyCustomerDetail {



    private String customerName;
    private String serviceCategoryName;
    private String status;
    private String lastUpdateAt;
    private String nextUpdate;
    private String dateCreated;
    private String link;
    private String mobile;
    private String email;
    private String sellEarnId;

    public MyCustomerDetail(String customerName, String serviceCategoryName, String status, String lastUpdateAt, String nextUpdate, String dateCreated, String link, String mobile, String email, String sellEarnId) {
        this.customerName = customerName;
        this.serviceCategoryName = serviceCategoryName;
        this.status = status;
        this.lastUpdateAt = lastUpdateAt;
        this.nextUpdate = nextUpdate;
        this.dateCreated = dateCreated;
        this.link = link;
        this.mobile = mobile;
        this.email = email;
        this.sellEarnId = sellEarnId;
    }

    public String customerName() {
        return customerName;
    }

    public String serviceCategoryName() {
        return serviceCategoryName;
    }

    public String status() {
        return status;
    }

    public String lastUpdateAt() {
        return lastUpdateAt;
    }

    public String nextUpdate() {
        return nextUpdate;
    }

    public String dateCreated() {
        return dateCreated;
    }

    public String link() {
        return link;
    }

    public String mobile() {
        return mobile;
    }

    public String email() {
        return email;
    }

    public String sellEarnId() {
        return sellEarnId;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", MyCustomerDetail.class.getSimpleName() + "[", "]")
                .add("customerName='" + customerName + "'")
                .add("serviceCategoryName='" + serviceCategoryName + "'")
                .add("status='" + status + "'")
                .add("lastUpdateAt='" + lastUpdateAt + "'")
                .add("nextUpdate='" + nextUpdate + "'")
                .add("dateCreated='" + dateCreated + "'")
                .add("link='" + link + "'")
                .add("mobile='" + mobile + "'")
                .add("email='" + email + "'")
                .add("sellEarnId='" + sellEarnId + "'")
                .toString();
    }
}
