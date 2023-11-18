package com.onetechsol.ipayment.databinding;

public interface PayoutCheckClickListener {

    void closeBottomSheet();
    void createBank();
    void openAccountList();
    void openMode();

    void submitPayout(String amount,String totalAmount,String tpin);
    void addBank(String bankName,String bankAcNo,String bankAccHolder,String banIfsc,String bankBranch);

}
