package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthAepsOpRequest {


    @SerializedName("long")
    @Expose
    private String longitude;

    @SerializedName("lat")
    @Expose
    private String latitude;
    private String capture;
    private String amt;
    private String mobile;
    private String aadhar;
    private String bank;
    private String mode;
    private String device;

    public AuthAepsOpRequest(String lon, String lat, String capture, String amt, String mobile, String aadhar, String bank, String mode, String device) {
        this.longitude = lon;
        this.latitude = lat;
        this.capture = capture;
        this.amt = amt;
        this.mobile = mobile;
        this.aadhar = aadhar;
        this.bank = bank;
        this.mode = mode;
        this.device = device;
    }
}
