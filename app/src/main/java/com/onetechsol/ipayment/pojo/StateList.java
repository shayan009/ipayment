package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateList {

    @SerializedName("label")
    @Expose
    public String label;


    public StateList(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public StateList setLabel(String label) {
        this.label = label;
        return this;
    }

}
