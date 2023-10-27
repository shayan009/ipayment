package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBeneficiaryRequest {

    @SerializedName("rem_mobile")
    @Expose
    private String remMobile;


    public GetBeneficiaryRequest(String remMobile) {
        this.remMobile = remMobile;
    }

    public String remMobile() {
        return remMobile;
    }

}
