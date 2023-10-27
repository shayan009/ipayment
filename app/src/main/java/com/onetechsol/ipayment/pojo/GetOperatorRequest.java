package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOperatorRequest {

    @SerializedName("service")
    @Expose
    private String serviceId;


    public GetOperatorRequest(String serviceId) {
        this.serviceId = serviceId;
    }

    public String serviceId() {
        return serviceId;
    }
}
