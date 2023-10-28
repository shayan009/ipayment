package com.onetechsol.ipayment.pojo;



public class MatmServiceRequest {


    private String mode;

    private String amt;

    public MatmServiceRequest(String mode, String amt) {
        this.mode = mode;
        this.amt = amt;
    }

    public String mode() {
        return mode;
    }

    public String amt() {
        return amt;
    }
}
