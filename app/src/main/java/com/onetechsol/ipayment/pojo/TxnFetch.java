package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TxnFetch {

    @SerializedName("data")
    @Expose
    private TxnFetchData txnFetchData;
}
