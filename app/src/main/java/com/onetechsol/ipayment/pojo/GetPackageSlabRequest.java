package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetPackageSlabRequest {

    @SerializedName("package_id")
    @Expose
    private String packageId;

    public GetPackageSlabRequest(String packageId) {
        this.packageId = packageId;
    }

    public String packageId() {
        return packageId;
    }
}
