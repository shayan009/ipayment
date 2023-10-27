package com.onetechsol.ipayment.databinding;

public interface RegisterClickListener {

    void goBack();

    void openSpinner();

    void register(String referral, String fullName, String email, String address, String pinCode, String district, String state);

}
