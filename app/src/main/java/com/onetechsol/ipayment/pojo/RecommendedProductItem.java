package com.onetechsol.ipayment.pojo;

public class RecommendedProductItem {

    private String name;
    private double amount;
    private String imagePath;

    private double reward;
    private SellEarnType sellEarnType;

    private String colorCard;
    private String colorText;


    public RecommendedProductItem(String name, double amount, String imagePath, SellEarnType sellEarnType, String colorCard, String colorText,double reward) {
        this.name = name;
        this.amount = amount;
        this.imagePath = imagePath;
        this.sellEarnType = sellEarnType;
        this.colorCard = colorCard;
        this.colorText = colorText;
        this.reward = reward;
    }

    public double getReward() {
        return reward;
    }

    public String colorCard() {
        return colorCard;
    }

    public String colorText() {
        return colorText;
    }

    public SellEarnType productType() {
        return sellEarnType;
    }

    public RecommendedProductItem setProductType(SellEarnType sellEarnType) {
        this.sellEarnType = sellEarnType;
        return this;
    }

    public RecommendedProductItem setColorCard(String colorCard) {
        this.colorCard = colorCard;
        return this;
    }

    public RecommendedProductItem setColorText(String colorText) {
        this.colorText = colorText;
        return this;
    }

    public String name() {
        return name;
    }

    public RecommendedProductItem setName(String name) {
        this.name = name;
        return this;
    }

    public double amount() {
        return amount;
    }

    public RecommendedProductItem setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String imagePath() {
        return imagePath;
    }

    public RecommendedProductItem setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }
}
