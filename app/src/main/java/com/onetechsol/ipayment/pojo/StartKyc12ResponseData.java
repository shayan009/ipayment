package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartKyc12ResponseData {


    @SerializedName("merchant")
    @Expose
    private String merchant;

    @SerializedName("partnerId")
    @Expose
    private String partnerId;

    @SerializedName("partnerKey")
    @Expose
    private String partnerKey;


    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("shopName")
    @Expose
    private String shopName;

    public String merchant() {
        return merchant;
    }

    public String partnerId() {
        return partnerId;
    }

    public String partnerKey() {
        return partnerKey;
    }

    public String mobile() {
        return mobile;
    }

    public String email() {
        return email;
    }

    public String shopName() {
        return shopName;
    }
}
