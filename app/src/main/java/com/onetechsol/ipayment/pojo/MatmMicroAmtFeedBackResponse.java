package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatmMicroAmtFeedBackResponse extends Response {


    private String txn;
    private String print_msg;

    @SerializedName("txn_status")
    @Expose
    private String txn_status;


    public String txn() {
        return txn;
    }

    public String print_msg() {
        return print_msg;
    }

    public String txn_status() {
        return txn_status;
    }
}
