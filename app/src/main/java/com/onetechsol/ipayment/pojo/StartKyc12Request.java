package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartKyc12Request {

    @SerializedName("Version")
    @Expose
    private String version;

    public StartKyc12Request(String version) {
        this.version = version;
    }
}
