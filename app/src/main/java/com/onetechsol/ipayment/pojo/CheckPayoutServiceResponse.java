package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Transient;

import java.util.List;

public class CheckPayoutServiceResponse extends Response {


    private String wallet;

    @SerializedName("wallet_lvl")
    @Expose
    private String walletLvl;

    @SerializedName("bank_status")
    @Expose
    private String bankStatus;


    @SerializedName("acc_avl")
    @Expose
    private String accAvl;


    @SerializedName("eligible_amt")
    @Expose
    private String eligibleAmt;

    @SerializedName("acc_list")
    @Expose
    private List<CircleBankModel> circleBankModelList;


    @SerializedName("mode_list")
    @Expose
    private List<PayoutMode> payoutModeList;


    public List<PayoutMode> getPayoutModeList() {
        return payoutModeList;
    }

    public List<CircleBankModel> getCircleBankModelList() {
        return circleBankModelList;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getWallet() {
        return wallet;
    }

    public String getWalletLvl() {
        return walletLvl;
    }

    public String getBankStatus() {
        return bankStatus;
    }

    public String getAccAvl() {
        return accAvl;
    }

    public String getEligibleAmt() {
        return eligibleAmt;
    }
}
