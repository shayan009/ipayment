package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOperatorCircleResponseData {


    @SerializedName("master_version")
    @Expose
    private int masterVersion;

    @SerializedName("company_version")
    @Expose
    private int companyVersion;

    @SerializedName("circle_list")
    @Expose
    private List<OperatorCircleItem> operatorCircleItems;

    public int masterVersion() {
        return masterVersion;
    }

    public int companyVersion() {
        return companyVersion;
    }

    public List<OperatorCircleItem> operatorCircleItems() {
        return operatorCircleItems;
    }
}
