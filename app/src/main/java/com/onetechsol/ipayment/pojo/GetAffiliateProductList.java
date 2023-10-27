package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAffiliateProductList extends Response {

    @SerializedName("data")
    @Expose
    GetAffiliateProductListData data;

    public GetAffiliateProductListData data() {
        return data;
    }
}
