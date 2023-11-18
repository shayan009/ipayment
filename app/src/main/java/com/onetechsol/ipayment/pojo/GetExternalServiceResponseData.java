package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetExternalServiceResponseData {

    @SerializedName("external_link")
    @Expose
    private String externalLink;

    public String getExternalLink() {
        return externalLink;
    }
}
