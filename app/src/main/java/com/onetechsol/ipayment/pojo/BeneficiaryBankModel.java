package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeneficiaryBankModel {

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("value")
    @Expose
    private String value;


    public String label() {
        return label;
    }

    public String value() {
        return value;
    }
}
