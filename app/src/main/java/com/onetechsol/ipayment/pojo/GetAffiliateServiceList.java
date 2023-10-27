package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAffiliateServiceList extends Response {

    @SerializedName("data")
    @Expose
    GetAffiliateServiceListData data;

    public GetAffiliateServiceListData data() {
        return data;
    }
}
