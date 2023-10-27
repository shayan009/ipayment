package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpResponse extends Response {


    @SerializedName("kyc_status")
    @Expose
    private String kycStatus;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("data")
    @Expose
    private VerifyResponseData data;

    public VerifyResponseData data() {
        return data;
    }

    public String kycStatus() {
        return kycStatus;
    }

    public VerifyOtpResponse setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
        return this;
    }

    public String token() {
        return token;
    }

    public VerifyOtpResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public VerifyOtpResponse setData(VerifyResponseData data) {
        this.data = data;
        return this;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VerifyOtpResponse{");
        sb.append("kycStatus='").append(kycStatus).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", data=").append(data);
        sb.append(", status='").append(status).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
