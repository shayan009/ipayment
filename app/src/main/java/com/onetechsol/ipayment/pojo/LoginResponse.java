package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResponse extends Response {


    @SerializedName("data")
    @Expose
    public LoginResponseData data;


    @SerializedName("kyc_status")
    @Expose
    public String kycStatus;


    @SerializedName("token")
    @Expose
    public String token;

    public LoginResponse setData(LoginResponseData data) {
        this.data = data;
        return this;
    }

    public String kycStatus() {
        return kycStatus;
    }

    public LoginResponse setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
        return this;
    }

    public String token() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public LoginResponseData data() {
        return data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginResponse{");
        sb.append("data=").append(data);
        sb.append(", kycStatus='").append(kycStatus).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
