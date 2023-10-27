package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPackageSlabResponse extends Response {

    @SerializedName("data")
    @Expose
    GetPackageSlabResponseData data;

    public GetPackageSlabResponseData data() {
        return data;
    }
}
