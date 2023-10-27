package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrepaidMobilePlansResponseData {

    @SerializedName("plan_list")
    @Expose
    private List<PrepaidMobilePlan> prepaidMobilePlanList;

    public List<PrepaidMobilePlan> prepaidMobilePlanList() {
        return prepaidMobilePlanList;
    }
}
