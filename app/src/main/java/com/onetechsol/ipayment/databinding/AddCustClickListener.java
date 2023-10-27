package com.onetechsol.ipayment.databinding;

public interface AddCustClickListener extends CommonOperation {


    void openEmployment();

    void openIncome();

    void openHasCC();

    void addCustomer(String name, String mobile, String email, String age, String pinCode);
}
