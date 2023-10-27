package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterUserRequest {

    @SerializedName("referral")
    @Expose
    private String referral;

    @SerializedName("user_type")
    @Expose
    private String userType;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("business_name")
    @Expose
    private String businessName;

    @SerializedName("mobile")
    @Expose
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

    @SerializedName("po")
    @Expose
    private String po;

    @SerializedName("ps")
    @Expose
    private String ps;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("district")
    @Expose
    private String district;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("pan")
    @Expose
    private String pan;

    @SerializedName("aadhar")
    @Expose
    private String aadhar;

    @SerializedName("dob")
    @Expose
    private String dob;


    public RegisterUserRequest(String referral, String userType, String name, String businessName, String mobile, String mobileAlter, String email, String address, String pinCode, String po, String ps, String city, String district, String state, String pan, String aadhar, String dob) {
        this.referral = referral;
        this.userType = userType;
        this.name = name;
        this.businessName = businessName;
        this.mobile = mobile;
        this.mobileAlter = mobileAlter;
        this.email = email;
        this.address = address;
        this.pinCode = pinCode;
        this.po = po;
        this.ps = ps;
        this.city = city;
        this.district = district;
        this.state = state;
        this.pan = pan;
        this.aadhar = aadhar;
        this.dob = dob;
    }

    public String referral() {
        return referral;
    }

    public RegisterUserRequest setReferral(String referral) {
        this.referral = referral;
        return this;
    }

    public String userType() {
        return userType;
    }

    public RegisterUserRequest setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public String name() {
        return name;
    }

    public RegisterUserRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String businessName() {
        return businessName;
    }

    public RegisterUserRequest setBusinessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public String mobile() {
        return mobile;
    }

    public RegisterUserRequest setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String mobileAlter() {
        return mobileAlter;
    }

    public RegisterUserRequest setMobileAlter(String mobileAlter) {
        this.mobileAlter = mobileAlter;
        return this;
    }

    public String email() {
        return email;
    }

    public RegisterUserRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String address() {
        return address;
    }

    public RegisterUserRequest setAddress(String address) {
        this.address = address;
        return this;
    }

    public String pinCode() {
        return pinCode;
    }

    public RegisterUserRequest setPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public String po() {
        return po;
    }

    public RegisterUserRequest setPo(String po) {
        this.po = po;
        return this;
    }

    public String ps() {
        return ps;
    }

    public RegisterUserRequest setPs(String ps) {
        this.ps = ps;
        return this;
    }

    public String city() {
        return city;
    }

    public RegisterUserRequest setCity(String city) {
        this.city = city;
        return this;
    }

    public String district() {
        return district;
    }

    public RegisterUserRequest setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String state() {
        return state;
    }

    public RegisterUserRequest setState(String state) {
        this.state = state;
        return this;
    }

    public String pan() {
        return pan;
    }

    public RegisterUserRequest setPan(String pan) {
        this.pan = pan;
        return this;
    }

    public String aadhar() {
        return aadhar;
    }

    public RegisterUserRequest setAadhar(String aadhar) {
        this.aadhar = aadhar;
        return this;
    }

    public String dob() {
        return dob;
    }

    public RegisterUserRequest setDob(String dob) {
        this.dob = dob;
        return this;
    }
}
