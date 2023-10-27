package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPackageSlabResponseData {

    @SerializedName("package_list")
    @Expose
    private List<PackageSlabItem> packageList;

    public List<PackageSlabItem> packageList() {
        return packageList;
    }
}
