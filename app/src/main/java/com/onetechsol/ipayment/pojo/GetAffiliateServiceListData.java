package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAffiliateServiceListData {

    @SerializedName("service_list")
    @Expose
    private List<AffiliateService> affiliateServiceList;

    public List<AffiliateService> affiliateServiceList() {
        return affiliateServiceList;
    }
}
