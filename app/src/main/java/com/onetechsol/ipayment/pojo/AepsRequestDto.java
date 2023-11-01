package com.onetechsol.ipayment.pojo;

public class AepsRequestDto {

    private String bankName;
    private String mobileNo;
    private int type;
    private String amount;
    private String adhar;


    public AepsRequestDto(String bankName, String mobileNo, int type, String amount, String adhar) {

        this.bankName = bankName;
        this.mobileNo = mobileNo;
        this.type = type;
        this.amount = amount;
        this.adhar = adhar;
    }

    public String getBankName() {
        return bankName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public int getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public String getAdhar() {
        return adhar;
    }
}
