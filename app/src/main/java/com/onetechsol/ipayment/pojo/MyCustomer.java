package com.onetechsol.ipayment.pojo;

import java.util.List;
import java.util.StringJoiner;

public class MyCustomer {

    private String serviceName;
    private boolean isSelected;
    private int count;


    private List<MyCustomerDetail> myCustomerDetailList;

    public MyCustomer(String serviceName, List<MyCustomerDetail> myCustomerDetailList, boolean isSelected) {
        this.serviceName = serviceName;
        this.myCustomerDetailList = myCustomerDetailList;
        this.isSelected = isSelected;
    }

    public int count() {
        return count;
    }

    public MyCustomer setCount(int count) {
        this.count = count;
        return this;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public MyCustomer setSelected(boolean selected) {
        isSelected = selected;
        return this;
    }

    public String serviceName() {
        return serviceName;
    }

    public List<MyCustomerDetail> myCustomerDetailList() {
        return myCustomerDetailList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MyCustomer.class.getSimpleName() + "[", "]")
                .add("serviceName='" + serviceName + "'")
                .add("isSelected=" + isSelected)
                .add("myCustomerDetailList=" + myCustomerDetailList)
                .toString();
    }

}
