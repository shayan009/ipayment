package com.onetechsol.ipayment.pojo;

public class MatmServiceResponseData {

    private String transactionType;
    private String amount;
    private String referenceNumber;
    private String latitude;
    private String longitude;
    private String supMerchantId;
    private String subMerchantId;
    private String subMerchantPass;
    private String deviceManufacturerId;
    private String mobileNumber;
    private String remarks;

    public String transactionType() {
        return transactionType;
    }

    public String amount() {
        return amount;
    }

    public String referenceNumber() {
        return referenceNumber;
    }

    public String latitude() {
        return latitude;
    }

    public String longitude() {
        return longitude;
    }

    public String supMerchantId() {
        return supMerchantId;
    }

    public String subMerchantId() {
        return subMerchantId;
    }

    public String subMerchantPass() {
        return subMerchantPass;
    }

    public String deviceManufacturerId() {
        return deviceManufacturerId;
    }

    public String mobileNumber() {
        return mobileNumber;
    }

    public String remarks() {
        return remarks;
    }
}
