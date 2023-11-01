package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AepsBankModel {


    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("label")
    @Expose
    private String name;

    public String value() {
        return value;
    }

    public String name() {
        return name;
    }
}
