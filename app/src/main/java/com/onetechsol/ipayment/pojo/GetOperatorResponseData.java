package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOperatorResponseData {


    @SerializedName("master_version")
    @Expose
    private int masterVersion;

    @SerializedName("company_version")
    @Expose
    private int companyVersion;

    @SerializedName("operator_plan")
    @Expose
    private String operatorPlan;

    @SerializedName("operator_list")
    @Expose
    private List<OperatorItem> operatorItemList;

    public int masterVersion() {
        return masterVersion;
    }

    public int companyVersion() {
        return companyVersion;
    }

    public String operatorPlan() {
        return operatorPlan;
    }

    public List<OperatorItem> operatorItemList() {
        return operatorItemList;
    }
}
