package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperatorItem {

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("img")
    @Expose
    private String img;

    public String label() {
        return label;
    }

    public String id() {
        return id;
    }

    public String img() {
        return img;
    }
}
