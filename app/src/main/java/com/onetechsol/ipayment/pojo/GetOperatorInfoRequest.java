package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOperatorInfoRequest {

    @SerializedName("operator")
    @Expose
    private String operator;


    @SerializedName("service_code")
    @Expose
    private String serviceCode;

    public GetOperatorInfoRequest(String operator, String serviceCode) {
        this.operator = operator;
        this.serviceCode = serviceCode;
    }

    public String operator() {
        return operator;
    }

    public String serviceCode() {
        return serviceCode;
    }
}
