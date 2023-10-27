package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseData {

    @SerializedName("master_version")
    @Expose
    public String masterVersion;

    @SerializedName("company_version")
    @Expose
    public String companyVersion;

    @SerializedName("profile")
    @Expose
    private UserProfile userProfile;

    @SerializedName("setup")
    @Expose
    private UserProfileSetup setup;


    public UserProfileSetup setup() {
        return setup;
    }

    public LoginResponseData setSetup(UserProfileSetup setup) {
        this.setup = setup;
        return this;
    }

    public UserProfile userProfile() {
        return userProfile;
    }

    public LoginResponseData setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public String masterVersion() {
        return masterVersion;
    }

    public LoginResponseData setMasterVersion(String masterVersion) {
        this.masterVersion = masterVersion;
        return this;
    }

    public String companyVersion() {
        return companyVersion;
    }

    public LoginResponseData setCompanyVersion(String companyVersion) {
        this.companyVersion = companyVersion;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginResponseData{");
        sb.append("masterVersion='").append(masterVersion).append('\'');
        sb.append(", companyVersion='").append(companyVersion).append('\'');
        sb.append(", userProfile=").append(userProfile);
        sb.append(", setup=").append(setup);
        sb.append('}');
        return sb.toString();
    }
}
