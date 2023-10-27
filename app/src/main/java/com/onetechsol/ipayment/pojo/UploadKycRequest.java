package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadKycRequest {


    @SerializedName("aadhar_up")
    @Expose
    public String aadharNo;

    @SerializedName("business_name_up")
    @Expose
    public String businessName;

    @SerializedName("source")
    @Expose
    public String source;


    public UploadKycRequest(String aadharNo, String businessName, String source) {
        this.aadharNo = aadharNo;
        this.businessName = businessName;
        this.source = source;
    }

    public String aadharNo() {
        return aadharNo;
    }

    public UploadKycRequest setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
        return this;
    }

    public String businessName() {
        return businessName;
    }

    public UploadKycRequest setBusinessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public String source() {
        return source;
    }

    public UploadKycRequest setSource(String source) {
        this.source = source;
        return this;
    }
}
