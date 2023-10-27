package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FetchProductListRequest {

    @SerializedName("sell_earn_id")
    @Expose
    public String sellEarnId;

    public FetchProductListRequest(String sellEarnId) {
        this.sellEarnId = sellEarnId;
    }

    public String sellEarnId() {
        return sellEarnId;
    }
}
