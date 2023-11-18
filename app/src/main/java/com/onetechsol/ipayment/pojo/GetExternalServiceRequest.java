package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetExternalServiceRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    private String id;


    public GetExternalServiceRequest(String version, String id) {
        this.version = version;
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public String getId() {
        return id;
    }
}
