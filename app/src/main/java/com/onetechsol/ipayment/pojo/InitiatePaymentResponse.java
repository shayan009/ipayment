package com.onetechsol.ipayment.pojo;

public class InitiatePaymentResponse extends Response {


    private String data;

    public String data() {
        return data;
    }

    public InitiatePaymentResponse setData(String data) {
        this.data = data;
        return this;
    }
}
