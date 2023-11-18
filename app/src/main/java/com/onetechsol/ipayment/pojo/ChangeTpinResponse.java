package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeTpinResponse extends Response {


    @SerializedName("security_f")
    @Expose
    private String securityf;


    public String getSecurityf() {
        return securityf;
    }
}
