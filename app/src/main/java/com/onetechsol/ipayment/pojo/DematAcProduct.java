package com.onetechsol.ipayment.pojo;

import java.util.List;

public class DematAcProduct {

    private String id;
    private String name;
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

    private String link;

    public DematAcProduct(String id, String link, String name, List<GoalModel> goalModels, double joinFee, double annualFee, String approvalRate, double maxEarn, String iconUrl, String shortName, String cardColor, String titleColor, String contentColor) {
        this.id = id;
        this.link = link;
        this.name = name;
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

    public String link() {
        return link;
    }

    public DematAcProduct setLink(String link) {
        this.link = link;
        return this;
    }

    public String titleColor() {
        return titleColor;
    }

    public DematAcProduct setTitleColor(String titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public String contentColor() {
        return contentColor;
    }

    public DematAcProduct setContentColor(String contentColor) {
        this.contentColor = contentColor;
        return this;
    }

    public DematAcProduct setId(String id) {
        this.id = id;
        return this;
    }

    public DematAcProduct setName(String name) {
        this.name = name;
        return this;
    }

    public DematAcProduct setRewards(List<GoalModel> goalModels) {
        this.goalModels = goalModels;
        return this;
    }

    public DematAcProduct setJoinFee(double joinFee) {
        this.joinFee = joinFee;
        return this;
    }

    public DematAcProduct setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
        return this;
    }

    public DematAcProduct setApprovalRate(String approvalRate) {
        this.approvalRate = approvalRate;
        return this;
    }

    public DematAcProduct setMaxEarn(double maxEarn) {
        this.maxEarn = maxEarn;
        return this;
    }

    public DematAcProduct setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public DematAcProduct setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String cardColor() {
        return cardColor;
    }

    public DematAcProduct setCardColor(String cardColor) {
        this.cardColor = cardColor;
        return this;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<GoalModel> rewards() {
        return goalModels;
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
}
