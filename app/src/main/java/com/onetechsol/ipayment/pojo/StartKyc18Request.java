package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartKyc18Request {

    @SerializedName("Version")
    @Expose
    private String version;

    private String step;
    private String otp;
    private String urlenco;

    @SerializedName("lat")
    @Expose
    private String latitude;

    @SerializedName("long")
    @Expose
    private String longitude;

    public StartKyc18Request(String version, String step, String otp, String urlenco, String latitude, String longitude) {
        this.version = version;
        this.step = step;
        this.otp = otp;
        this.urlenco = urlenco;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUrlenco() {
        return urlenco;
    }

    public void setUrlenco(String urlenco) {
        this.urlenco = urlenco;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
