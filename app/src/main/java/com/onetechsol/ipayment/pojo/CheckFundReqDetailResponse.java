package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.SerializedName;

public class CheckFundReqDetailResponse extends Response {

    @SerializedName("acc_list")
    FundReqAccount fundReqAccount;

    public FundReqAccount fundReqAccount() {
        return fundReqAccount;
    }
}
