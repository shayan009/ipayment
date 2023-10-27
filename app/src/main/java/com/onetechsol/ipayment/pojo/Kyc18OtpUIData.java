package com.onetechsol.ipayment.pojo;

public class Kyc18OtpUIData {

    private boolean isCapture;
    private String titleText;
    private String subTitleText;
    private String buttonText;


    public Kyc18OtpUIData(boolean isCapture, String titleText, String subTitleText, String buttonText) {
        this.isCapture = isCapture;
        this.titleText = titleText;
        this.subTitleText = subTitleText;
        this.buttonText = buttonText;
    }

    public boolean isCapture() {
        return isCapture;
    }

    public String titleText() {
        return titleText;
    }

    public String subTitleText() {
        return subTitleText;
    }

    public String buttonText() {
        return buttonText;
    }
}
