package com.onetechsol.ipayment.databinding;

public interface ChangeTpinClickListener {

    void closeBottomSheet();

    void submit(String otp, String newPin, String confirmNewPin);

}
