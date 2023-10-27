package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckRemitterResponseData {


    @SerializedName("rem_help")
    @Expose
    private String remHelp;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("name")
    @Expose
    private String name;

    public String name() {
        return name;
    }

    public String remHelp() {
        return remHelp;
    }

    public String mobile() {
        return mobile;
    }
}
