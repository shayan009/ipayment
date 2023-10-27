package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.SerializedName;

public class GetReportTypeResponse extends Response {


    @SerializedName("data")
    public GetReportTypeResponseData data;


    public GetReportTypeResponseData data() {
        return data;
    }
}
