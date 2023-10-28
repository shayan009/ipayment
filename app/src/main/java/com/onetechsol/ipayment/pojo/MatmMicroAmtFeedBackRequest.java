package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatmMicroAmtFeedBackRequest {


    private boolean status;
    private String message;
    private String response;
    private double transAmount;
    private double balAmount;
    private String bankRrn;
    private String transType;
    private int type;
    private String cardNum;
    private String rrn;
    @SerializedName("Ttype")
    @Expose
    private String tType;
    private String bankName;
    private String cardType;
    private String terminalId;
    private String fpId;
    private String transId;
    private String txn;

    public MatmMicroAmtFeedBackRequest() {
    }


    public boolean status() {
        return status;
    }

    public MatmMicroAmtFeedBackRequest setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public String message() {
        return message;
    }

    public MatmMicroAmtFeedBackRequest setMessage(String message) {
        this.message = message;
        return this;
    }

    public String response() {
        return response;
    }

    public MatmMicroAmtFeedBackRequest setResponse(String response) {
        this.response = response;
        return this;
    }

    public double transAmount() {
        return transAmount;
    }

    public MatmMicroAmtFeedBackRequest setTransAmount(double transAmount) {
        this.transAmount = transAmount;
        return this;
    }

    public double balAmount() {
        return balAmount;
    }

    public MatmMicroAmtFeedBackRequest setBalAmount(double balAmount) {
        this.balAmount = balAmount;
        return this;
    }

    public String bankRrn() {
        return bankRrn;
    }

    public MatmMicroAmtFeedBackRequest setBankRrn(String bankRrn) {
        this.bankRrn = bankRrn;
        return this;
    }

    public String transType() {
        return transType;
    }

    public MatmMicroAmtFeedBackRequest setTransType(String transType) {
        this.transType = transType;
        return this;
    }

    public int type() {
        return type;
    }

    public MatmMicroAmtFeedBackRequest setType(int type) {
        this.type = type;
        return this;
    }

    public String cardNum() {
        return cardNum;
    }

    public MatmMicroAmtFeedBackRequest setCardNum(String cardNum) {
        this.cardNum = cardNum;
        return this;
    }

    public String rrn() {
        return rrn;
    }

    public MatmMicroAmtFeedBackRequest setRrn(String rrn) {
        this.rrn = rrn;
        return this;
    }

    public String tType() {
        return tType;
    }

    public MatmMicroAmtFeedBackRequest settType(String tType) {
        this.tType = tType;
        return this;
    }

    public String bankName() {
        return bankName;
    }

    public MatmMicroAmtFeedBackRequest setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String cardType() {
        return cardType;
    }

    public MatmMicroAmtFeedBackRequest setCardType(String cardType) {
        this.cardType = cardType;
        return this;
    }

    public String terminalId() {
        return terminalId;
    }

    public MatmMicroAmtFeedBackRequest setTerminalId(String terminalId) {
        this.terminalId = terminalId;
        return this;
    }

    public String fpId() {
        return fpId;
    }

    public MatmMicroAmtFeedBackRequest setFpId(String fpId) {
        this.fpId = fpId;
        return this;
    }

    public String transId() {
        return transId;
    }

    public MatmMicroAmtFeedBackRequest setTransId(String transId) {
        this.transId = transId;
        return this;
    }

    public String txn() {
        return txn;
    }

    public MatmMicroAmtFeedBackRequest setTxn(String txn) {
        this.txn = txn;
        return this;
    }
}
