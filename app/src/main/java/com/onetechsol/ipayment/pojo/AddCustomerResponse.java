package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.SerializedName;

public class AddCustomerResponse extends Response {


    @SerializedName("reload_url")
    public String reloadUrl;

    public String reloadUrl() {
        return reloadUrl;
    }
}
