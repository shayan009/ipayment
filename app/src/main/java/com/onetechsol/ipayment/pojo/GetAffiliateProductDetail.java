package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAffiliateProductDetail extends Response {

    @SerializedName("data")
    @Expose
    GetAffiliateProductDetailData data;

    public GetAffiliateProductDetailData data() {
        return data;
    }
}
