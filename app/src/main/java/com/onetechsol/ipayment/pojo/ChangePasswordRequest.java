package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {

    @SerializedName("old_password")
    @Expose
    private String oldPassword;

    @SerializedName("new_password")
    @Expose
    private String newPassword;

    @SerializedName("confirm_password")
    @Expose
    private String confirmPassword;

    @SerializedName("security_s")
    @Expose
    private String security;

    private String tpin;

    private String otp;


    public ChangePasswordRequest(String oldPassword, String newPassword, String confirmPassword, String security, String tpin, String otp) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.security = security;
        this.tpin = tpin;
        this.otp = otp;
    }
}
