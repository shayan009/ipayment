package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AEPS1ReportResponse extends Response {

    @SerializedName("data")
    @Expose
    private AEPS1ReportResponseData data;


    public AEPS1ReportResponse() {
    }


    public AEPS1ReportResponse(AEPS1ReportResponseData data) {
        this.data = data;
    }

    public AEPS1ReportResponse setData(AEPS1ReportResponseData data) {
        this.data = data;
        return this;
    }

    public AEPS1ReportResponseData data() {
        return data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AEPS2ReportResponse{");
        sb.append("data=").append(data);
        sb.append(", status='").append(status).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
