package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartKyc12Request {

    @SerializedName("Version")
    @Expose
    private String version;

    @SerializedName("capture")
    @Expose
    private String urlenco;

    @SerializedName("lat")
    @Expose
    private String latitude;

    @SerializedName("long")
    @Expose
    private String longitude;

    private String step;

    public StartKyc12Request(String version) {
        this.version = version;
    }

    public StartKyc12Request(String urlenco, String latitude, String longitude,String step) {
        this.urlenco = urlenco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.step = step;
    }

    public String getVersion() {
        return version;
    }

    public String getUrlenco() {
        return urlenco;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
