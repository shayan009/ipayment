package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCustomerRequest {

    @SerializedName("sell_earn_id")
    @Expose
    private String sellEarnId;

    @SerializedName("status")
    @Expose
    private String status;

    public GetCustomerRequest(String sellEarnId, String status) {
        this.sellEarnId = sellEarnId;
        this.status = status;
    }
}
