package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBeneficiaryResponse extends Response {

    @SerializedName("txn_status")
    @Expose
    private String txnStatus;

    @SerializedName("data")
    @Expose
    private GetBeneficiaryResponseData data;

    public String txnStatus() {
        return txnStatus;
    }

    public GetBeneficiaryResponseData data() {
        return data;
    }
}
