package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {


    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("role_id")
    @Expose
    private String roleId;
    private String name;

    @SerializedName("business_name")
    @Expose
    private String businessName;

    @SerializedName("business_address")
    @Expose
    private String businessAddress;

    private String mobile;

    @SerializedName("mobile_alter")
    @Expose
    private String mobileAlter;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("pin_code")
    @Expose
    private String pinCode;


    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("pan")
    @Expose
    private String pan;

    @SerializedName("aadhar")
    @Expose
    private String aadhar;

    @SerializedName("dob")
    @Expose
    private String dob;


    public String userName() {
        return userName;
    }

    public UserProfile setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String roleId() {
        return roleId;
    }

    public UserProfile setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String name() {
        return name;
    }

    public UserProfile setName(String name) {
        this.name = name;
        return this;
    }

    public String businessName() {
        return businessName;
    }

    public UserProfile setBusinessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public String businessAddress() {
        return businessAddress;
    }

    public UserProfile setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
        return this;
    }

    public String mobile() {
        return mobile;
    }

    public UserProfile setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String mobileAlter() {
        return mobileAlter;
    }

    public UserProfile setMobileAlter(String mobileAlter) {
        this.mobileAlter = mobileAlter;
        return this;
    }

    public String email() {
        return email;
    }

    public UserProfile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String address() {
        return address;
    }

    public UserProfile setAddress(String address) {
        this.address = address;
        return this;
    }

    public String pinCode() {
        return pinCode;
    }

    public UserProfile setPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public String state() {
        return state;
    }

    public UserProfile setState(String state) {
        this.state = state;
        return this;
    }

    public String city() {
        return city;
    }

    public UserProfile setCity(String city) {
        this.city = city;
        return this;
    }

    public String pan() {
        return pan;
    }

    public UserProfile setPan(String pan) {
        this.pan = pan;
        return this;
    }

    public String aadhar() {
        return aadhar;
    }

    public UserProfile setAadhar(String aadhar) {
        this.aadhar = aadhar;
        return this;
    }

    public String dob() {
        return dob;
    }

    public UserProfile setDob(String dob) {
        this.dob = dob;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserProfile{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", roleId='").append(roleId).append('\'');
        sb.append(", name='").append(name).append('\'');
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
        sb.append('}');
        return sb.toString();
    }
}
