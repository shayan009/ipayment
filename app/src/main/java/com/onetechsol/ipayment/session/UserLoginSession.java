package com.onetechsol.ipayment.session;

public class UserLoginSession {


    private String userName;
    private String roleId;
    private String loginName;
    private String businessName;
    private String businessAddress;
    private String mobile;
    private String mobileAlter;
    private String email;
    private String address;
    private String pinCode;
    private String state;
    private String city;
    private String pan;
    private String aadhar;
    private String dob;
    private String token;
    private String masterVersion;
    private String companyVersion;
    private String androidId;
    private String videoLink;

    private String upgradeAmount;


    public UserLoginSession(String userName, String roleId, String loginName, String businessName, String businessAddress, String mobile, String mobileAlter, String email, String address, String pinCode, String state, String city, String pan, String aadhar, String dob, String token, String masterVersion, String companyVersion, String androidId, String videoLink, String upgradeAmount) {
        this.userName = userName;
        this.roleId = roleId;
        this.loginName = loginName;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.mobile = mobile;
        this.mobileAlter = mobileAlter;
        this.email = email;
        this.address = address;
        this.pinCode = pinCode;
        this.state = state;
        this.city = city;
        this.pan = pan;
        this.aadhar = aadhar;
        this.dob = dob;
        this.token = token;
        this.masterVersion = masterVersion;
        this.companyVersion = companyVersion;
        this.androidId = androidId;
        this.videoLink = videoLink;
        this.upgradeAmount = upgradeAmount;
    }

    public String upgradeAmount() {
        return upgradeAmount;
    }

    public String userName() {
        return userName;
    }

    public String roleId() {
        return roleId;
    }

    public String loginName() {
        return loginName;
    }

    public String businessName() {
        return businessName;
    }

    public String businessAddress() {
        return businessAddress;
    }

    public String mobile() {
        return mobile;
    }

    public String mobileAlter() {
        return mobileAlter;
    }

    public String email() {
        return email;
    }

    public String address() {
        return address;
    }

    public String pinCode() {
        return pinCode;
    }

    public String state() {
        return state;
    }

    public String city() {
        return city;
    }

    public String pan() {
        return pan;
    }

    public String aadhar() {
        return aadhar;
    }

    public String dob() {
        return dob;
    }

    public String token() {
        return token;
    }

    public String masterVersion() {
        return masterVersion;
    }

    public String companyVersion() {
        return companyVersion;
    }

    public String androidId() {
        return androidId;
    }

    public String videoLink() {
        return videoLink;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserSession{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", roleId='").append(roleId).append('\'');
        sb.append(", loginName='").append(loginName).append('\'');
        sb.append(", businessName='").append(businessName).append('\'');
        sb.append(", businessAddress='").append(businessAddress).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", mobileAlter='").append(mobileAlter).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", pinCode='").append(pinCode).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", pan='").append(pan).append('\'');
        sb.append(", aadhar='").append(aadhar).append('\'');
        sb.append(", dob='").append(dob).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", masterVersion='").append(masterVersion).append('\'');
        sb.append(", companyVersion='").append(companyVersion).append('\'');
        sb.append(", androidId='").append(androidId).append('\'');
        sb.append(", videoLink='").append(videoLink).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
