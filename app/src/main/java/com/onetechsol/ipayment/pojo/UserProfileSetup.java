package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileSetup {


    @SerializedName("kyc")
    @Expose
    private String kyc;

    @SerializedName("kyc_remarks")
    @Expose
    private String kycRemarks;

    @SerializedName("video_link")
    @Expose
    private String videoLink;

    public String kyc() {
        return kyc;
    }

    public UserProfileSetup setKyc(String kyc) {
        this.kyc = kyc;
        return this;
    }

    public String kycRemarks() {
        return kycRemarks;
    }

    public UserProfileSetup setKycRemarks(String kycRemarks) {
        this.kycRemarks = kycRemarks;
        return this;
    }

    public String videoLink() {
        return videoLink;
    }

    public UserProfileSetup setVideoLink(String videoLink) {
        this.videoLink = videoLink;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserProfileSetup{");
        sb.append("kyc='").append(kyc).append('\'');
        sb.append(", kycRemarks='").append(kycRemarks).append('\'');
        sb.append(", videoLink='").append(videoLink).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
