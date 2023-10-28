package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.karneim.pojobuilder.GeneratePojoBuilder;


@GeneratePojoBuilder
public class VerifyOtpRequest {

    @SerializedName("Otp")
    @Expose
    private String otp;

    @SerializedName("firebase")
    @Expose
    private String firebase;

    public VerifyOtpRequest(String otp, String firebase) {
        this.otp = otp;
        this.firebase = firebase;
    }

    public String otp() {
        return otp;
    }

    public VerifyOtpRequest setOtp(String otp) {
        this.otp = otp;
        return this;
    }

    public String firebase() {
        return firebase;
    }

    public VerifyOtpRequest setFirebase(String firebase) {
        this.firebase = firebase;
        return this;
    }
}
