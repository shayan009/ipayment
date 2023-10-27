package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BuyKitRequestRequest {

    @SerializedName("package")
    @Expose
    private String packageName;

    @SerializedName("kit_qty")
    @Expose
    private String kitQuantity;

    @SerializedName("user_name")
    @Expose
    private String userName;


    public BuyKitRequestRequest(String packageName, String kitQuantity, String userName) {
        this.packageName = packageName;
        this.kitQuantity = kitQuantity;
        this.userName = userName;
    }

    public String packageName() {
        return packageName;
    }

    public String kitQuantity() {
        return kitQuantity;
    }

    public String userName() {
        return userName;
    }
}
