package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOperatorCircleRequest {

    @SerializedName("Version")
    @Expose
    private String version;


    public GetOperatorCircleRequest(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }
}
