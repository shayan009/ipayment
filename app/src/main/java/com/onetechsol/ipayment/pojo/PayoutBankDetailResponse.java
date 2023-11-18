package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayoutBankDetailResponse extends Response {

    @SerializedName("main_array")
    @Expose
    private List<PayoutBankModel> bankList;

    public List<PayoutBankModel> getBankList() {
        return bankList;
    }

    @Override
    public String toString() {
        return "PayoutBankDetailResponse{" +
                "bankList=" + bankList +
                '}';
    }
}
