package com.onetechsol.ipayment.pojo;

public class WalletBalanceModel {

    private WalletType walletType;
    private String walletName;
    private double balance;

    private String image;

    public WalletBalanceModel(WalletType walletType, String walletName, double balance, String image) {
        this.walletType = walletType;
        this.walletName = walletName;
        this.balance = balance;
        this.image = image;
    }

    public WalletType walletType() {
        return walletType;
    }

    public String walletName() {
        return walletName;
    }

    public WalletBalanceModel setWalletType(WalletType walletType) {
        this.walletType = walletType;
        return this;
    }

    public WalletBalanceModel setWalletName(String walletName) {
        this.walletName = walletName;
        return this;
    }

    public WalletBalanceModel setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public String image() {
        return image;
    }

    public WalletBalanceModel setImage(String image) {
        this.image = image;
        return this;
    }

    public double balance() {
        return balance;
    }
}
