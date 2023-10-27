package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOperatorInfoResponse extends Response {


    @SerializedName("data")
    @Expose
    private GetOperatorInfoResponseData data;

    public GetOperatorInfoResponseData data() {
        return data;
    }
}
