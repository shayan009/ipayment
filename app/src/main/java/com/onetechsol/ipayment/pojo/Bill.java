package com.onetechsol.ipayment.pojo;

public class Bill {

    private String billNo;
    private String billId;
    private String dueDate;
    private String amount;
    private String billMonths;

    public Bill(String billNo, String billId, String dueDate, String amount, String billMonths) {
        this.billNo = billNo;
        this.billId = billId;
        this.dueDate = dueDate;
        this.amount = amount;
        this.billMonths = billMonths;
    }

    public Bill setBillNo(String billNo) {
        this.billNo = billNo;
        return this;
    }

    public Bill setBillId(String billId) {
        this.billId = billId;
        return this;
    }

    public Bill setDueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Bill setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public Bill setBillMonths(String billMonths) {
        this.billMonths = billMonths;
        return this;
    }

    public String billNo() {
        return billNo;
    }

    public String billId() {
        return billId;
    }

    public String dueDate() {
        return dueDate;
    }

    public String amount() {
        return amount;
    }

    public String billMonths() {
        return billMonths;
    }
}
