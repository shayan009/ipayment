package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.StringJoiner;

public class ReportTypeItem {


    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("color")
    @Expose
    private String color;

    @Override
    public String toString() {
        return new StringJoiner(", ", ReportTypeItem.class.getSimpleName() + "[", "]")
                .add("label='" + label + "'")
                .add("id='" + id + "'")
                .add("color='" + color + "'")
                .toString();
    }

    public String label() {
        return label;
    }

    public String id() {
        return id;
    }

    public String color() {
        return color;
    }
}
