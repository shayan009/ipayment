package com.onetechsol.ipayment.dto;

import java.io.Serializable;

public class LoginViewData implements Serializable {

    private String buttonText;
    private boolean otpFieldVisible;

    public LoginViewData(String buttonText, boolean otpFieldVisible) {
        this.buttonText = buttonText;
        this.otpFieldVisible = otpFieldVisible;
    }

    public String buttonText() {
        return buttonText;
    }

    public LoginViewData setButtonText(String buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    public boolean otpFieldVisible() {
        return otpFieldVisible;
    }

    public LoginViewData setOtpFieldVisible(boolean otpFieldVisible) {
        this.otpFieldVisible = otpFieldVisible;
        return this;
    }
}
