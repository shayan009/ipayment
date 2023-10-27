package com.onetechsol.ipayment.pojo;

public class MyCustCategoryItem {

    private String name;
    private int count;

    public MyCustCategoryItem(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String name() {
        return name;
    }

    public int count() {
        return count;
    }
}
