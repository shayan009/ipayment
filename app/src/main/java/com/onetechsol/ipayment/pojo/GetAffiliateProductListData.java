package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAffiliateProductListData extends Response {


    @SerializedName("service_list")
    @Expose
    private List<AffiliateProductModel> productModelList;

    public List<AffiliateProductModel> productModelList() {
        return productModelList;
    }

    public GetAffiliateProductListData setProductModelList(List<AffiliateProductModel> productModelList) {
        this.productModelList = productModelList;
        return this;
    }
}
