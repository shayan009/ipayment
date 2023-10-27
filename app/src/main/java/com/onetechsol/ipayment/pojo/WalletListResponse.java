package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletListResponse extends Response {

    @SerializedName("data")
    @Expose
    private WalletListResponseData data;

    @SerializedName("logout")
    @Expose
    private String logout;

    public WalletListResponse() {
    }

    public WalletListResponse(WalletListResponseData data) {
        this.data = data;
    }

    public WalletListResponse setData(WalletListResponseData data) {
        this.data = data;
        return this;
    }

    public String logout() {
        return logout;
    }

    public WalletListResponse setLogout(String logout) {
        this.logout = logout;
        return this;
    }

    public WalletListResponseData data() {
        return data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WalletListResponseData{");
        sb.append("data=").append(data);
        sb.append(", status='").append(status).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
