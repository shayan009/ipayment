package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetReportTypeRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public GetReportTypeRequest(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }

    public GetReportTypeRequest setVersion(String version) {
        this.version = version;
        return this;
    }

}
