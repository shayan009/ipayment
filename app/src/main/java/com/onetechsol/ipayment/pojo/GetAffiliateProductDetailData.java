package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetAffiliateProductDetailData extends Response implements Parcelable {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("sell_earn_id")
    @Expose
    private String sellEarnId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("is_active")
    @Expose
    private boolean isActive;

    @SerializedName("Benefits")
    @Expose
    private ArrayList<BenefitModel> benefitModels;

    @SerializedName("Audiences")
    @Expose
    private ArrayList<WhomToSellModel> whomToSellModels;


    @SerializedName("Instructions")
    @Expose
    private ArrayList<InstructionModel> instructionModels;


    @SerializedName("Terms")
    @Expose
    private ArrayList<TermsConditionsModel> termsConditionsModels;

    @SerializedName("Goals")
    @Expose
    private ArrayList<GoalModel> goalModels;

    @SerializedName("Images")
    @Expose
    private ArrayList<ContentModel> images;

    @SerializedName("Videos")
    @Expose
    private ArrayList<ContentModel> videoList;

    @SerializedName("opening_charge")
    @Expose
    private double joinFee;

    @SerializedName("annual_fee")
    @Expose
    private double annualFee;

    @SerializedName("approval_rate")
    @Expose
    private String approvalRate;

    @SerializedName("earning")
    @Expose
    private double maxEarn;


    @SerializedName("rate_of_interest")
    @Expose
    private String interest;


    @SerializedName("image")
    private String iconUrl;

    @SerializedName("short_name")
    @Expose
    private String shortName;

    @SerializedName("is_instant_upi_available")
    private String isInstantUpiAvailable;

    @SerializedName("is_vdc_available")
    private String isVdcAvailable;

    @SerializedName("min_account_bal")
    @Expose
    private String minAccountBalance;

    @SerializedName("creation_time")
    private String accountCreationTime;

    @SerializedName("type")
    @Expose
    private String type;

    private String link;


    public GetAffiliateProductDetailData setImages(ArrayList<ContentModel> images) {
        this.images = images;
        return this;
    }

    protected GetAffiliateProductDetailData(Parcel in) {
        id = in.readString();
        sellEarnId = in.readString();
        name = in.readString();
        isActive = in.readByte() != 0;
        benefitModels = in.createTypedArrayList(BenefitModel.CREATOR);
        whomToSellModels = in.createTypedArrayList(WhomToSellModel.CREATOR);
        instructionModels = in.createTypedArrayList(InstructionModel.CREATOR);
        termsConditionsModels = in.createTypedArrayList(TermsConditionsModel.CREATOR);
        goalModels = in.createTypedArrayList(GoalModel.CREATOR);
        images = in.createTypedArrayList(ContentModel.CREATOR);
        videoList = in.createTypedArrayList(ContentModel.CREATOR);
        joinFee = in.readDouble();
        annualFee = in.readDouble();
        approvalRate = in.readString();
        maxEarn = in.readDouble();
        interest = in.readString();
        iconUrl = in.readString();
        shortName = in.readString();
        isInstantUpiAvailable = in.readString();
        isVdcAvailable = in.readString();
        minAccountBalance = in.readString();
        accountCreationTime = in.readString();
        type = in.readString();
        link = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(sellEarnId);
        dest.writeString(name);
        dest.writeByte((byte) (isActive ? 1 : 0));
        dest.writeTypedList(benefitModels);
        dest.writeTypedList(whomToSellModels);
        dest.writeTypedList(instructionModels);
        dest.writeTypedList(termsConditionsModels);
        dest.writeTypedList(goalModels);
        dest.writeTypedList(images);
        dest.writeTypedList(videoList);
        dest.writeDouble(joinFee);
        dest.writeDouble(annualFee);
        dest.writeString(approvalRate);
        dest.writeDouble(maxEarn);
        dest.writeString(interest);
        dest.writeString(iconUrl);
        dest.writeString(shortName);
        dest.writeString(isInstantUpiAvailable);
        dest.writeString(isVdcAvailable);
        dest.writeString(minAccountBalance);
        dest.writeString(accountCreationTime);
        dest.writeString(type);
        dest.writeString(link);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GetAffiliateProductDetailData> CREATOR = new Creator<GetAffiliateProductDetailData>() {
        @Override
        public GetAffiliateProductDetailData createFromParcel(Parcel in) {
            return new GetAffiliateProductDetailData(in);
        }

        @Override
        public GetAffiliateProductDetailData[] newArray(int size) {
            return new GetAffiliateProductDetailData[size];
        }
    };

    public String link() {
        return link;
    }

    public String id() {
        return id;
    }

    public String sellEarnId() {
        return sellEarnId;
    }

    public String name() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public ArrayList<BenefitModel> benefitModels() {
        return benefitModels;
    }

    public ArrayList<WhomToSellModel> whomToSellModels() {
        return whomToSellModels;
    }

    public ArrayList<InstructionModel> instructionModels() {
        return instructionModels;
    }

    public ArrayList<TermsConditionsModel> termsConditionsModels() {
        return termsConditionsModels;
    }

    public ArrayList<GoalModel> goalModels() {
        return goalModels;
    }

    public ArrayList<ContentModel> images() {
        return images;
    }

    public ArrayList<ContentModel> videoList() {
        return videoList;
    }

    public double joinFee() {
        return joinFee;
    }

    public double annualFee() {
        return annualFee;
    }

    public String approvalRate() {
        return approvalRate;
    }

    public double maxEarn() {
        return maxEarn;
    }

    public String interest() {
        return interest;
    }

    public String iconUrl() {
        return iconUrl;
    }

    public String shortName() {
        return shortName;
    }

    public String isInstantUpiAvailable() {
        return isInstantUpiAvailable;
    }

    public String isVdcAvailable() {
        return isVdcAvailable;
    }

    public String minAccountBalance() {
        return minAccountBalance;
    }

    public String accountCreationTime() {
        return accountCreationTime;
    }

    public String type() {
        return type;
    }
}
