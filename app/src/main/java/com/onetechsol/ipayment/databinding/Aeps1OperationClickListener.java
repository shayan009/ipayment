package com.onetechsol.ipayment.databinding;

public interface Aeps1OperationClickListener {

    void goBack();

    void submitAepsOperation(String bankName,String mobileNo,String adhar,String amount);
    void  openBankList();
    void openDevice();
    void openMode();

}
