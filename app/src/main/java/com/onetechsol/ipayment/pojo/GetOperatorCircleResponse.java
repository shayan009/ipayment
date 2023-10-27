package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOperatorCircleResponse extends Response {


    @SerializedName("data")
    @Expose
    private GetOperatorCircleResponseData data;

    public GetOperatorCircleResponseData data() {
        return data;
    }
}
