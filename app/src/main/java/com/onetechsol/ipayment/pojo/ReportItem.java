package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportItem {


    @SerializedName("cnt")
    @Expose
    private int cnt;

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("date")
    @Expose
    private String date;


    @SerializedName("txn")
    @Expose
    private String txn;

    @SerializedName("txn_type")
    @Expose
    private String txnType;

    @SerializedName("wal_o")
    @Expose
    private String wallletOBal;


    @SerializedName("amt")
    @Expose
    private String amt;


    @SerializedName("wal")
    @Expose
    private String wal;

    @SerializedName("wal_type")
    @Expose
    private String walType;

    @SerializedName("txn_number")
    @Expose
    private String txnNumber;

    @SerializedName("bank")
    @Expose
    private String bank;


    @SerializedName("ifsc")
    @Expose
    private String ifsc;

    @SerializedName("txn_customer_no")
    @Expose
    private String txnCustomerNo;

    @SerializedName("txn_customer_name")
    @Expose
    private String txnCustomerName;

    @SerializedName("txn_status")
    @Expose
    private String txnStatus;

    @SerializedName("txn_print")
    @Expose
    private String txnPrint;

    @SerializedName("operator")
    @Expose
    private String operator;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("aadhar")
    @Expose
    private String aadhar;

    public int cnt() {
        return cnt;
    }

    public String user() {
        return user;
    }

    public String date() {
        return date;
    }

    public String txn() {
        return txn;
    }

    public String txnType() {
        return txnType;
    }

    public String wallletOBal() {
        return wallletOBal;
    }

    public String amt() {
        return amt;
    }

    public String wal() {
        return wal;
    }

    public String walType() {
        return walType;
    }

    public String txnNumber() {
        return txnNumber;
    }

    public String bank() {
        return bank;
    }

    public String ifsc() {
        return ifsc;
    }

    public String txnCustomerNo() {
        return txnCustomerNo;
    }

    public String txnCustomerName() {
        return txnCustomerName;
    }

    public String txnStatus() {
        return txnStatus;
    }

    public String txnPrint() {
        return txnPrint;
    }

    public String operator() {
        return operator;
    }

    public String remarks() {
        return remarks;
    }

    public String aadhar() {
        return aadhar;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AEPS1ReportItem{");
        sb.append("cnt=").append(cnt);
        sb.append(", user='").append(user).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", txn='").append(txn).append('\'');
        sb.append(", txnType='").append(txnType).append('\'');
        sb.append(", wallletOBal='").append(wallletOBal).append('\'');
        sb.append(", amt='").append(amt).append('\'');
        sb.append(", wal='").append(wal).append('\'');
        sb.append(", walType='").append(walType).append('\'');
        sb.append(", txnNumber='").append(txnNumber).append('\'');
        sb.append(", bank='").append(bank).append('\'');
        sb.append(", ifsc='").append(ifsc).append('\'');
        sb.append(", txnCustomerNo='").append(txnCustomerNo).append('\'');
        sb.append(", txnCustomerName='").append(txnCustomerName).append('\'');
        sb.append(", txnStatus='").append(txnStatus).append('\'');
        sb.append(", txnPrint='").append(txnPrint).append('\'');
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", aadhar='").append(aadhar).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
