package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CheckLoginResponse extends Response {

    @SerializedName("status_list")
    @Expose
    public StatusList statusList;

    @SerializedName("logout")
    @Expose
    public int logout;


    @SerializedName("kyc_status")
    @Expose
    public int kycStatus;

    @SerializedName("kyc_status_msg")
    @Expose
    public String kycStatusMsg;

    @SerializedName("kyc_message")
    @Expose
    public String kycMessage;


    @SerializedName("kyc_banking_status")
    @Expose
    public String kycBankingStatus;

    @SerializedName("kyc_banking_status_msg")
    @Expose
    public String kycBankingStatusMsg;

    @SerializedName("kyc_banking_page")
    @Expose
    public int kycBankingPage;


    @SerializedName("page_list")
    @Expose
    public PageList pageList;

    @SerializedName("support_list")
    @Expose
    public SupportList supportList;


    public int kycStatus() {
        return kycStatus;
    }

    public CheckLoginResponse setKycStatus(int kycStatus) {
        this.kycStatus = kycStatus;
        return this;
    }

    public String kycBankingStatusMsg() {
        return kycBankingStatusMsg;
    }

    public CheckLoginResponse setKycBankingStatusMsg(String kycBankingStatusMsg) {
        this.kycBankingStatusMsg = kycBankingStatusMsg;
        return this;
    }

    public String kycStatusMsg() {
        return kycStatusMsg;
    }

    public CheckLoginResponse setKycStatusMsg(String kycStatusMsg) {
        this.kycStatusMsg = kycStatusMsg;
        return this;
    }

    public String kycMessage() {
        return kycMessage;
    }

    public CheckLoginResponse setKycMessage(String kycMessage) {
        this.kycMessage = kycMessage;
        return this;
    }

    public String kycBankingStatus() {
        return kycBankingStatus;
    }

    public CheckLoginResponse setKycBankingStatus(String kycBankingStatus) {
        this.kycBankingStatus = kycBankingStatus;
        return this;
    }

    public int kycBankingPage() {
        return kycBankingPage;
    }

    public CheckLoginResponse setKycBankingPage(int kycBankingPage) {
        this.kycBankingPage = kycBankingPage;
        return this;
    }

    public StatusList statusList() {
        return statusList;
    }

    public int logout() {
        return logout;
    }

    public PageList pageList() {
        return pageList;
    }

    public SupportList supportList() {
        return supportList;
    }

    public CheckLoginResponse setStatusList(StatusList statusList) {
        this.statusList = statusList;
        return this;
    }

    public CheckLoginResponse setLogout(int logout) {
        this.logout = logout;
        return this;
    }

    public CheckLoginResponse setPageList(PageList pageList) {
        this.pageList = pageList;
        return this;
    }

    public CheckLoginResponse setSupportList(SupportList supportList) {
        this.supportList = supportList;
        return this;
    }

    @Override
    public String toString() {
        return "CheckLoginResponse{" +
                "statusList=" + statusList +
                ", logout=" + logout +
                ", pageList=" + pageList +
                ", supportList=" + supportList +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
