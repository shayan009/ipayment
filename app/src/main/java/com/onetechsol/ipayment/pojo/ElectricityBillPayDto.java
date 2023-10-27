package com.onetechsol.ipayment.pojo;

public class ElectricityBillPayDto {


    private String txnNumber = "";
    private String txnNumberName = "";
    private String txnNumberRegex = "";
    private String txnCustomerNo = "";
    private String txnAd1 = "";
    private String txnAd1Name = "";
    private String txnAd1Regex = "";
    private String txnAd2 = "";
    private String txnAd2Name = "";
    private String txnAd2Regex = "";
    private String txnAd3 = "";
    private String txnAd3Name = "";
    private String txnAd3Regex = "";
    private String txnAd4 = "";

    private String operatorTitle;
    private String title;

    private boolean txnNumberShow = false;
    private boolean txnCustomerNoShow = false;
    private boolean txnAd1Show = false;
    private boolean txnAd2Show = false;
    private boolean txnAd3Show = false;
    private boolean txnAd4Show = false;

    public ElectricityBillPayDto() {
    }

    public ElectricityBillPayDto(String operatorTitle, String title) {
        this.operatorTitle = operatorTitle;
        this.title = title;

    }

    public boolean txnNumberShow() {
        return txnNumberShow;
    }

    public ElectricityBillPayDto setTxnNumberShow(boolean txnNumberShow) {
        this.txnNumberShow = txnNumberShow;
        return this;
    }

    public boolean txnCustomerNoShow() {
        return txnCustomerNoShow;
    }

    public ElectricityBillPayDto setTxnCustomerNoShow(boolean txnCustomerNoShow) {
        this.txnCustomerNoShow = txnCustomerNoShow;
        return this;
    }

    public boolean txnAd1Show() {
        return txnAd1Show;
    }

    public ElectricityBillPayDto setTxnAd1Show(boolean txnAd1Show) {
        this.txnAd1Show = txnAd1Show;
        return this;
    }

    public boolean txnAd2Show() {
        return txnAd2Show;
    }

    public ElectricityBillPayDto setTxnAd2Show(boolean txnAd2Show) {
        this.txnAd2Show = txnAd2Show;
        return this;
    }

    public boolean txnAd3Show() {
        return txnAd3Show;
    }

    public ElectricityBillPayDto setTxnAd3Show(boolean txnAd3Show) {
        this.txnAd3Show = txnAd3Show;
        return this;
    }

    public boolean txnAd4Show() {
        return txnAd4Show;
    }

    public ElectricityBillPayDto setTxnAd4Show(boolean txnAd4Show) {
        this.txnAd4Show = txnAd4Show;
        return this;
    }

    public String txnNumber() {
        return txnNumber;
    }

    public ElectricityBillPayDto setTxnNumber(String txnNumber) {
        this.txnNumber = txnNumber;
        return this;
    }

    public String txnNumberName() {
        return txnNumberName;
    }

    public ElectricityBillPayDto setTxnNumberName(String txnNumberName) {
        this.txnNumberName = txnNumberName;
        return this;
    }

    public String txnNumberRegex() {
        return txnNumberRegex;
    }

    public ElectricityBillPayDto setTxnNumberRegex(String txnNumberRegex) {
        this.txnNumberRegex = txnNumberRegex;
        return this;
    }

    public String txnCustomerNo() {
        return txnCustomerNo;
    }

    public ElectricityBillPayDto setTxnCustomerNo(String txnCustomerNo) {
        this.txnCustomerNo = txnCustomerNo;
        return this;
    }

    public String txnAd1() {
        return txnAd1;
    }

    public ElectricityBillPayDto setTxnAd1(String txnAd1) {
        this.txnAd1 = txnAd1;
        return this;
    }

    public String txnAd1Name() {
        return txnAd1Name;
    }

    public ElectricityBillPayDto setTxnAd1Name(String txnAd1Name) {
        this.txnAd1Name = txnAd1Name;
        return this;
    }

    public String txnAd1Regex() {
        return txnAd1Regex;
    }

    public ElectricityBillPayDto setTxnAd1Regex(String txnAd1Regex) {
        this.txnAd1Regex = txnAd1Regex;
        return this;
    }

    public String txnAd2() {
        return txnAd2;
    }

    public ElectricityBillPayDto setTxnAd2(String txnAd2) {
        this.txnAd2 = txnAd2;
        return this;
    }

    public String txnAd2Name() {
        return txnAd2Name;
    }

    public ElectricityBillPayDto setTxnAd2Name(String txnAd2Name) {
        this.txnAd2Name = txnAd2Name;
        return this;
    }

    public String txnAd2Regex() {
        return txnAd2Regex;
    }

    public ElectricityBillPayDto setTxnAd2Regex(String txnAd2Regex) {
        this.txnAd2Regex = txnAd2Regex;
        return this;
    }

    public String txnAd3() {
        return txnAd3;
    }

    public ElectricityBillPayDto setTxnAd3(String txnAd3) {
        this.txnAd3 = txnAd3;
        return this;
    }

    public String txnAd3Name() {
        return txnAd3Name;
    }

    public ElectricityBillPayDto setTxnAd3Name(String txnAd3Name) {
        this.txnAd3Name = txnAd3Name;
        return this;
    }

    public String txnAd3Regex() {
        return txnAd3Regex;
    }

    public ElectricityBillPayDto setTxnAd3Regex(String txnAd3Regex) {
        this.txnAd3Regex = txnAd3Regex;
        return this;
    }

    public String txnAd4() {
        return txnAd4;
    }

    public ElectricityBillPayDto setTxnAd4(String txnAd4) {
        this.txnAd4 = txnAd4;
        return this;
    }

    public String operatorTitle() {
        return operatorTitle;
    }

    public ElectricityBillPayDto setOperatorTitle(String operatorTitle) {
        this.operatorTitle = operatorTitle;
        return this;
    }

    public String title() {
        return title;
    }

    public ElectricityBillPayDto setTitle(String title) {
        this.title = title;
        return this;
    }
}
