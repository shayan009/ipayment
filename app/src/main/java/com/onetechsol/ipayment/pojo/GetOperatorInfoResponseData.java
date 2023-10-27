package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOperatorInfoResponseData {

    @SerializedName("view_bill")
    @Expose
    private String viewBill;

    @SerializedName("circle")
    @Expose
    private String circle;


    @SerializedName("roffer")
    @Expose
    private String roffer;

    @SerializedName("view_plan")
    @Expose
    private String viewPlan;

    @SerializedName("circle_view_plan")
    @Expose
    private String circleViewPlan;

    @SerializedName("dthInfo")
    @Expose
    private String dthInfo;


    @SerializedName("operator_id")
    @Expose
    private String operatorId;

    @SerializedName("operator")
    @Expose
    private String operator;

    @SerializedName("inp_list")
    @Expose
    private List<OperatorInfoItem> operatorInfoItemList;

    public String operatorId() {
        return operatorId;
    }

    public String operator() {
        return operator;
    }

    public List<OperatorInfoItem> operatorInfoItemList() {
        return operatorInfoItemList;
    }

    public String viewBill() {
        return viewBill;
    }

    public String circle() {
        return circle;
    }

    public String roffer() {
        return roffer;
    }

    public String viewPlan() {
        return viewPlan;
    }

    public String circleViewPlan() {
        return circleViewPlan;
    }

    public String dthInfo() {
        return dthInfo;
    }
}
