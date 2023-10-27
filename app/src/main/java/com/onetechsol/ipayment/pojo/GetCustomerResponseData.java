package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCustomerResponseData {

    @SerializedName("customer")
    @Expose
    private List<MyCustomerDetail> getCustomerList;

    public List<MyCustomerDetail> getCustomerList() {
        return getCustomerList;
    }
}
