package com.onetechsol.ipayment.pojo;

public class InsuranceProduct {

    private int id;
    private String imgUrl;
    private String name;
    private boolean active;

    public InsuranceProduct(int id, String imgUrl, String name, boolean active) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.name = name;
        this.active = active;
    }


    public boolean active() {
        return active;
    }

    public int id() {
        return id;
    }

    public String imgUrl() {
        return imgUrl;
    }

    public String name() {
        return name;
    }
}
