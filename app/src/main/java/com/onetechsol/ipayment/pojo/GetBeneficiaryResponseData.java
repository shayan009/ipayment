package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBeneficiaryResponseData {


    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("bene_list")
    @Expose
    private List<BeneficiaryModel> beneficiaryList;

    public String mobile() {
        return mobile;
    }


    public List<BeneficiaryModel> beneficiaryList() {
        return beneficiaryList;
    }
}
