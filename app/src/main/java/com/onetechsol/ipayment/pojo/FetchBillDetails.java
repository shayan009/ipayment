package com.onetechsol.ipayment.pojo;

import java.util.ArrayList;
import java.util.List;

public class FetchBillDetails extends Response {

    private String mobile = "";
    private String customerId = "";
    private String customerLabel = "";
    private String customerName = "";
    private OpCircleItemDto opCircleItemDto = null;
    private OpCircleItemDto circleItemDto = null;
    private ServiceCategoryModel serviceCategoryModel = null;
    private List<Bill> billList = new ArrayList<>();

    private String txnFetch = "";
    private String txnNumber = "";
    private String txnCustomerNo = "";
    private String txnAd1 = "";
    private String txnAd2 = "";
    private String txnAd3 = "";
    private String txnAd4 = "";
    private ElectricityBillPayDto electricityBillPayDto = new ElectricityBillPayDto();

    public OpCircleItemDto circleItemDto() {
        return circleItemDto;
    }

    public FetchBillDetails setCircleItemDto(OpCircleItemDto circleItemDto) {
        this.circleItemDto = circleItemDto;
        return this;
    }

    public String txnNumber() {
        return txnNumber;
    }

    public FetchBillDetails setTxnNumber(String txnNumber) {
        this.txnNumber = txnNumber;
        return this;
    }

    public String txnCustomerNo() {
        return txnCustomerNo;
    }

    public FetchBillDetails setTxnCustomerNo(String txnCustomerNo) {
        this.txnCustomerNo = txnCustomerNo;
        return this;
    }

    public String txnAd1() {
        return txnAd1;
    }

    public FetchBillDetails setTxnAd1(String txnAd1) {
        this.txnAd1 = txnAd1;
        return this;
    }

    public String txnAd2() {
        return txnAd2;
    }

    public FetchBillDetails setTxnAd2(String txnAd2) {
        this.txnAd2 = txnAd2;
        return this;
    }

    public String txnAd3() {
        return txnAd3;
    }

    public FetchBillDetails setTxnAd3(String txnAd3) {
        this.txnAd3 = txnAd3;
        return this;
    }

    public String txnAd4() {
        return txnAd4;
    }

    public FetchBillDetails setTxnAd4(String txnAd4) {
        this.txnAd4 = txnAd4;
        return this;
    }

    public String txnFetch() {
        return txnFetch;
    }

    public FetchBillDetails setTxnFetch(String txnFetch) {
        this.txnFetch = txnFetch;
        return this;
    }

    public ElectricityBillPayDto electricityBillPayDto() {
        return electricityBillPayDto;
    }

    public FetchBillDetails setElectricityBillPayDto(ElectricityBillPayDto electricityBillPayDto) {
        this.electricityBillPayDto = electricityBillPayDto;
        return this;
    }

    public String customerLabel() {
        return customerLabel;
    }

    public FetchBillDetails setCustomerLabel(String customerLabel) {
        this.customerLabel = customerLabel;
        return this;
    }

    public List<Bill> billList() {
        return billList;
    }

    public FetchBillDetails setBillList(List<Bill> billList) {
        this.billList = billList;
        return this;
    }

    public String mobile() {
        return mobile;
    }

    public FetchBillDetails setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String customerId() {
        return customerId;
    }

    public FetchBillDetails setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String customerName() {
        return customerName;
    }

    public FetchBillDetails setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public OpCircleItemDto opCircleItemDto() {
        return opCircleItemDto;
    }

    public FetchBillDetails setOpCircleItemDto(OpCircleItemDto opCircleItemDto) {
        this.opCircleItemDto = opCircleItemDto;
        return this;
    }

    public ServiceCategoryModel serviceCategoryModel() {
        return serviceCategoryModel;
    }

    public FetchBillDetails setServiceCategoryModel(ServiceCategoryModel serviceCategoryModel) {
        this.serviceCategoryModel = serviceCategoryModel;
        return this;
    }
}
