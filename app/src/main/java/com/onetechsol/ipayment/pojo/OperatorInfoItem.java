package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperatorInfoItem {


    @SerializedName("view")
    @Expose
    private String view;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("txn_number")
    @Expose
    private String txnNumber;

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("max_len")
    @Expose
    private String maxLength;

    @SerializedName("min_len")
    @Expose
    private String minLength;

    @SerializedName("regex")
    @Expose
    private String regex;

    public String view() {
        return view;
    }

    public String name() {
        return name;
    }

    public String txnNumber() {
        return txnNumber;
    }

    public String label() {
        return label;
    }

    public String maxLength() {
        return maxLength;
    }

    public String minLength() {
        return minLength;
    }

    public String regex() {
        return regex;
    }
}
