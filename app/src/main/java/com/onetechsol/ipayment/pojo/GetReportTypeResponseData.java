package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetReportTypeResponseData {

    @SerializedName("service_list")
    @Expose
    private List<ReportTypeItem> reportTypeList;

    public List<ReportTypeItem> reportTypeList() {
        return reportTypeList;
    }
}
