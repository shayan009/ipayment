package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOperatorResponse extends Response {


    @SerializedName("data")
    @Expose
    private GetOperatorResponseData data;

    public GetOperatorResponseData data() {
        return data;
    }
}
