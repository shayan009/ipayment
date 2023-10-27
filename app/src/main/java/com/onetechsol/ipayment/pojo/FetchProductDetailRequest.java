package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FetchProductDetailRequest {

    @SerializedName("id")
    @Expose
    public String id;

    public FetchProductDetailRequest(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
