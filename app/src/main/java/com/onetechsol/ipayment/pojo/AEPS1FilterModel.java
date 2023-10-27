package com.onetechsol.ipayment.pojo;

import java.util.List;

public class AEPS1FilterModel {

    private List<Integer> months;
    private String fromDate;
    private String toDate;


    public List<Integer> months() {
        return months;
    }

    public AEPS1FilterModel setMonths(List<Integer> months) {
        this.months = months;
        return this;
    }

    public String fromDate() {
        return fromDate;
    }

    public AEPS1FilterModel setFromDate(String fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public String toDate() {
        return toDate;
    }

    public AEPS1FilterModel setToDate(String toDate) {
        this.toDate = toDate;
        return this;
    }
}
