package com.onetechsol.ipayment.pojo;

public class AcademyItem {
    private int id;
    private String name;
    private String type;
    private String trainingTime;

    public AcademyItem(int id, String name, String type, String trainingTime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.trainingTime = trainingTime;
    }

    public AcademyItem() {
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public String trainingTime() {
        return trainingTime;
    }
}
