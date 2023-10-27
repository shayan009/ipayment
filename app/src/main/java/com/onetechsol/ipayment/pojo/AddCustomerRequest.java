package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCustomerRequest {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("pin")
    @Expose
    private String pin;


    @SerializedName("service")
    @Expose
    private String service;

    @SerializedName("employment_type")
    @Expose
    private String employmentType;

    @SerializedName("age")
    @Expose
    private String age;


    @SerializedName("income_range")
    @Expose
    private String incomeRange;


    @SerializedName("have_cc")
    @Expose
    private boolean haveCC;

    public AddCustomerRequest(String name, String mobile, String email, String pin, String service, String employmentType, String age, String incomeRange, boolean haveCC) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.pin = pin;
        this.service = service;
        this.employmentType = employmentType;
        this.age = age;
        this.incomeRange = incomeRange;
        this.haveCC = haveCC;
    }

    public String name() {
        return name;
    }

    public String mobile() {
        return mobile;
    }

    public String email() {
        return email;
    }

    public String pin() {
        return pin;
    }

    public String service() {
        return service;
    }

    public String employmentType() {
        return employmentType;
    }

    public String age() {
        return age;
    }

    public String incomeRange() {
        return incomeRange;
    }

    public boolean haveCC() {
        return haveCC;
    }
}
