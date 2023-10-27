package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppSetupData {


    @SerializedName("user_field")
    @Expose
    private UserField userField;

    @SerializedName("master_version")
    @Expose
    private int masterVersion;

    @SerializedName("company_version")
    @Expose
    private int companyVersion;

    @SerializedName("service")
    @Expose
    private ServiceItem serviceItem;

    @SerializedName("signup_list")
    @Expose
    private List<SignUpList> signupList;

    @SerializedName("state_list")
    @Expose
    private List<StateList> stateList;

    public UserField userField() {
        return userField;
    }

    public int masterVersion() {
        return masterVersion;
    }

    public int companyVersion() {
        return companyVersion;
    }

    public ServiceItem serviceItem() {
        return serviceItem;
    }

    public List<SignUpList> signupList() {
        return signupList;
    }

    public List<StateList> stateList() {
        return stateList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppSetupData{");
        sb.append("userField=").append(userField);
        sb.append(", masterVersion=").append(masterVersion);
        sb.append(", companyVersion=").append(companyVersion);
        sb.append(", serviceItem=").append(serviceItem);
        sb.append(", signupList=").append(signupList);
        sb.append(", stateList=").append(stateList);
        sb.append('}');
        return sb.toString();
    }
}
