package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartKyc18Request {

    @SerializedName("Version")
    @Expose
    private String version;

    @SerializedName("step")
    @Expose
    private String step;

    @SerializedName("otp")
    @Expose
    private String otp;

    public StartKyc18Request(String version, String step) {
        this.version = version;
        this.step = step;
    }

    public StartKyc18Request(String version, String step, String otp) {
        this.version = version;
        this.step = step;
        this.otp = otp;
    }

    public String version() {
        return version;
    }

    public StartKyc18Request setVersion(String version) {
        this.version = version;
        return this;
    }

    public String step() {
        return step;
    }

    public StartKyc18Request setStep(String step) {
        this.step = step;
        return this;
    }

    public String otp() {
        return otp;
    }

    public StartKyc18Request setOtp(String otp) {
        this.otp = otp;
        return this;
    }
}
