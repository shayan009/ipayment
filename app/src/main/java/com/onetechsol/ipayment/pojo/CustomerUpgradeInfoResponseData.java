package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerUpgradeInfoResponseData {

    @SerializedName("learner_amt")
    @Expose
    String learnerAmt;

    @SerializedName("img")
    @Expose
    String img;


    public String learnerAmt() {
        return learnerAmt;
    }

    public String img() {
        return img;
    }
}
