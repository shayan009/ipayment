package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AffiliateService {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("img")
    @Expose
    private String img;

    public String id() {
        return id;
    }

    public String label() {
        return label;
    }

    public String img() {
        return img;
    }
}
