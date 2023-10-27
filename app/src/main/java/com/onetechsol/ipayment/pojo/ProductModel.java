package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class ProductModel implements Parcelable {

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };
    private String id;
    private String name;
    private List<BenefitModel> rewards;
    private List<GoalModel> goalModels;
    private double joinFee;
    private double annualFee;
    private String approvalRate;
    private double maxEarn;
    private String iconUrl;
    private String shortName;
    private String cardColor;
    private String titleColor;
    private String contentColor;
    private double minAccountBalance;
    private int accountCreationTime;
    private SellEarnType sellEarnType;
    private String type;
    private String link;


    public ProductModel(String id, String type, SellEarnType sellEarnType, double minAccountBalance, int accountCreationTime, String link, String name, List<BenefitModel> rewards, List<GoalModel> goalModels, double joinFee, double annualFee, String approvalRate, double maxEarn, String iconUrl, String shortName, String cardColor, String titleColor, String contentColor) {
        this.id = id;
        this.type = type;
        this.sellEarnType = sellEarnType;
        this.minAccountBalance = minAccountBalance;
        this.accountCreationTime = accountCreationTime;
        this.link = link;
        this.name = name;
        this.rewards = rewards;
        this.goalModels = goalModels;
        this.joinFee = joinFee;
        this.annualFee = annualFee;
        this.approvalRate = approvalRate;
        this.maxEarn = maxEarn;
        this.iconUrl = iconUrl;
        this.shortName = shortName;
        this.cardColor = cardColor;
        this.titleColor = titleColor;
        this.contentColor = contentColor;
    }

    protected ProductModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        rewards = in.createTypedArrayList(BenefitModel.CREATOR);
        goalModels = in.createTypedArrayList(GoalModel.CREATOR);
        joinFee = in.readDouble();
        annualFee = in.readDouble();
        approvalRate = in.readString();
        maxEarn = in.readDouble();
        iconUrl = in.readString();
        shortName = in.readString();
        cardColor = in.readString();
        titleColor = in.readString();
        contentColor = in.readString();
        minAccountBalance = in.readDouble();
        accountCreationTime = in.readInt();
        type = in.readString();
        link = in.readString();
    }

    public double minAccountBalance() {
        return minAccountBalance;
    }

    public int accountCreationTime() {
        return accountCreationTime;
    }

    public SellEarnType sellEarnType() {
        return sellEarnType;
    }

    public String link() {
        return link;
    }

    public ProductModel setLink(String link) {
        this.link = link;
        return this;
    }

    public ProductModel setGoalModels(List<GoalModel> goalModels) {
        this.goalModels = goalModels;
        return this;
    }

    public ProductModel setMinAccountBalance(double minAccountBalance) {
        this.minAccountBalance = minAccountBalance;
        return this;
    }

    public ProductModel setAccountCreationTime(int accountCreationTime) {
        this.accountCreationTime = accountCreationTime;
        return this;
    }

    public ProductModel setSellEarnType(SellEarnType sellEarnType) {
        this.sellEarnType = sellEarnType;
        return this;
    }

    public String type() {
        return type;
    }

    public ProductModel setType(String type) {
        this.type = type;
        return this;
    }

    public String titleColor() {
        return titleColor;
    }

    public ProductModel setTitleColor(String titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public String contentColor() {
        return contentColor;
    }

    public ProductModel setContentColor(String contentColor) {
        this.contentColor = contentColor;
        return this;
    }

    public ProductModel setId(String id) {
        this.id = id;
        return this;
    }

    public ProductModel setName(String name) {
        this.name = name;
        return this;
    }

    public ProductModel setRewards(List<BenefitModel> rewards) {
        this.rewards = rewards;
        return this;
    }

    public ProductModel setJoinFee(double joinFee) {
        this.joinFee = joinFee;
        return this;
    }

    public ProductModel setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
        return this;
    }

    public ProductModel setApprovalRate(String approvalRate) {
        this.approvalRate = approvalRate;
        return this;
    }

    public ProductModel setMaxEarn(double maxEarn) {
        this.maxEarn = maxEarn;
        return this;
    }

    public ProductModel setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public ProductModel setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String cardColor() {
        return cardColor;
    }

    public ProductModel setCardColor(String cardColor) {
        this.cardColor = cardColor;
        return this;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<BenefitModel> rewards() {
        return rewards;
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

    public String iconUrl() {
        return iconUrl;
    }

    public String shortName() {
        return shortName;
    }

    public List<GoalModel> goalModels() {
        return goalModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeTypedList(rewards);
        parcel.writeTypedList(goalModels);
        parcel.writeDouble(joinFee);
        parcel.writeDouble(annualFee);
        parcel.writeString(approvalRate);
        parcel.writeDouble(maxEarn);
        parcel.writeString(iconUrl);
        parcel.writeString(shortName);
        parcel.writeString(cardColor);
        parcel.writeString(titleColor);
        parcel.writeString(contentColor);
        parcel.writeDouble(minAccountBalance);
        parcel.writeInt(accountCreationTime);
        parcel.writeString(type);
        parcel.writeString(link);
    }
}
