package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartKyc12CallbackRequest {

    private String merchant;
    private String status;
    private String response;
    private String message;

    public StartKyc12CallbackRequest(String merchant, String status, String response, String message) {
        this.merchant = merchant;
        this.status = status;
        this.response = response;
        this.message = message;
    }
}
