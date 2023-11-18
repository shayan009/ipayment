package com.onetechsol.ipayment.databinding;

public interface PayoutAddBankClickListener {

    void closeBottomSheet();

    void openBankList();

    void addBank(String bankName,String bankAcNo,String bankAccHolder,String banIfsc,String bankBranch);

}
