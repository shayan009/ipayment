package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletListRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    public WalletListRequest(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }

    public WalletListRequest setVersion(String version) {
        this.version = version;
        return this;
    }

}
