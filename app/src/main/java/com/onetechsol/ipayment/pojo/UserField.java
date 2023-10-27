package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserField {

    @SerializedName("referral")
    @Expose
    private String referral;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("business_name")
    @Expose
    private String businessName;


    public String referral() {
        return referral;
    }

    public String name() {
        return name;
    }

    public String businessName() {
        return businessName;
    }
}
