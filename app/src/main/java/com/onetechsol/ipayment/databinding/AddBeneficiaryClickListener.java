package com.onetechsol.ipayment.databinding;

public interface AddBeneficiaryClickListener {

    void openSpinner();

    void addBeneficiary(String bankName, String ifsc, String account, String name);

}
