package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeTPinRequest {

    @SerializedName("new_tpin")
    @Expose
    private String newTpin;

    @SerializedName("confirm_tpin")
    @Expose
    private String confirmTpin;

    @SerializedName("security_s")
    @Expose
    private String security;

    private String tpin;

    private String otp;

    public ChangeTPinRequest(String newTpin, String confirmTpin, String security, String tpin, String otp) {
        this.newTpin = newTpin;
        this.confirmTpin = confirmTpin;
        this.security = security;
        this.tpin = tpin;
        this.otp = otp;
    }

    public String getNewTpin() {
        return newTpin;
    }

    public String getConfirmTpin() {
        return confirmTpin;
    }

    public String getSecurity() {
        return security;
    }

    public String getTpin() {
        return tpin;
    }

    public String getOtp() {
        return otp;
    }
}
