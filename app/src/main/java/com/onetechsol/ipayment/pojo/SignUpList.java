package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpList {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("label")
    @Expose
    public String label;

    public int id() {
        return id;
    }

    public String label() {
        return label;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SignUpList{");
        sb.append("id=").append(id);
        sb.append(", label=").append(label);
        sb.append('}');
        return sb.toString();
    }
}
