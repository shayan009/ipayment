package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperatorCircleItem {

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("id")
    @Expose
    private String id;

    public String label() {
        return label;
    }

    public String id() {
        return id;
    }
}
