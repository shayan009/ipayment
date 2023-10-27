package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartKyc12Response extends Response {

    @SerializedName("data")
    @Expose
    private StartKyc12ResponseData data;

    public StartKyc12ResponseData data() {
        return data;
    }

    public StartKyc12Response setData(StartKyc12ResponseData data) {
        this.data = data;
        return this;
    }
}
