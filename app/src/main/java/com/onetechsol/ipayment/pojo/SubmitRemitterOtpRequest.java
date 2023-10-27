package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SubmitRemitterOtpRequest {

    @SerializedName("rem_mobile")
    @Expose
    private String remMobile;

    @SerializedName("rem_name")
    @Expose
    private String remName;

    @SerializedName("rem_otp")
    @Expose
    private String remOtp;

    @SerializedName("rem_help")
    @Expose
    private String remHelp;

    public SubmitRemitterOtpRequest(String remMobile, String remName, String remOtp, String remHelp) {
        this.remMobile = remMobile;
        this.remName = remName;
        this.remOtp = remOtp;
        this.remHelp = remHelp;
    }

    public String remMobile() {
        return remMobile;
    }

    public String remName() {
        return remName;
    }

    public String remOtp() {
        return remOtp;
    }

    public String remHelp() {
        return remHelp;
    }
}
