package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AppSetupResponse extends Response {

    @SerializedName("data")
    @Expose
    private AppSetupData data;

    public AppSetupData data() {
        return data;
    }
}
