package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyResponseData {

    @SerializedName("master_version")
    @Expose
    private String masterVersion;

    @SerializedName("company_version")
    @Expose
    private String companyVersion;


    @SerializedName("profile")
    @Expose
    private UserProfile userProfile;


    @SerializedName("setup")
    @Expose
    private UserProfileSetup setup;


    public UserProfile userProfile() {
        return userProfile;
    }

    public VerifyResponseData setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public UserProfileSetup setup() {
        return setup;
    }

    public VerifyResponseData setSetup(UserProfileSetup setup) {
        this.setup = setup;
        return this;
    }

    public String masterVersion() {
        return masterVersion;
    }

    public VerifyResponseData setMasterVersion(String masterVersion) {
        this.masterVersion = masterVersion;
        return this;
    }

    public String companyVersion() {
        return companyVersion;
    }

    public VerifyResponseData setCompanyVersion(String companyVersion) {
        this.companyVersion = companyVersion;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VerifyResponseData{");
        sb.append("masterVersion='").append(masterVersion).append('\'');
        sb.append(", companyVersion='").append(companyVersion).append('\'');
        sb.append(", userProfile=").append(userProfile);
        sb.append(", setup=").append(setup);
        sb.append('}');
        return sb.toString();
    }
}
