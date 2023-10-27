package com.onetechsol.ipayment.pojo;

public class AEPS1TaskModel {

    private int id;
    private String img;
    private String name;

    public AEPS1TaskModel(int id, String img, String name) {
        this.id = id;
        this.img = img;
        this.name = name;
    }

    public int id() {
        return id;
    }

    public String img() {
        return img;
    }

    public String name() {
        return name;
    }
}
