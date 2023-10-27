package com.onetechsol.ipayment.pojo;

public class ProductFilterItem {

    public String name;

    public ProductFilterItem(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public ProductFilterItem setName(String name) {
        this.name = name;
        return this;
    }
}
