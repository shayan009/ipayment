package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckFundReqResponse extends Response {

    @SerializedName("acc_list")
    List<FundReqAccount> fundReqAccounts;

    public List<FundReqAccount> fundReqAccounts() {
        return fundReqAccounts;
    }
}
