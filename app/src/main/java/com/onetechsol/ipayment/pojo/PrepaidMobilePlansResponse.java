package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrepaidMobilePlansResponse extends Response {


    @SerializedName("data")
    @Expose
    private PrepaidMobilePlansResponseData data;

    public PrepaidMobilePlansResponseData data() {
        return data;
    }
}
