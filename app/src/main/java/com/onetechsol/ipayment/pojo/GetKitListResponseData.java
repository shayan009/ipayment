package com.onetechsol.ipayment.pojo;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.StringJoiner;

public class GetKitListResponseData {

    @SerializedName("service_list")
    @Expose
    private List<KitData> kitDataList;


    @SerializedName("retl_status")
    @Expose
    private String retailerStatus;

    public String retailerStatus() {
        return retailerStatus;
    }

    public List<KitData> kitDataList() {
        return kitDataList;
    }

    @NonNull
    @Override
    public String toString() {
        return new StringJoiner(", ", GetKitListResponseData.class.getSimpleName() + "[", "]")
                .add("kitDataList=" + kitDataList)
                .toString();
    }
}
