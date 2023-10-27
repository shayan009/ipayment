package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.StringJoiner;

public class ServiceList {


    @SerializedName("servType")
    @Expose
    private String servType;

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("color")
    @Expose
    private String color;


    public String servType() {
        return servType;
    }

    public String label() {
        return label;
    }

    public String id() {
        return id;
    }

    public String img() {
        return img;
    }

    public String color() {
        return color;
    }

    public ServiceList setServType(String servType) {
        this.servType = servType;
        return this;
    }

    public ServiceList setLabel(String label) {
        this.label = label;
        return this;
    }

    public ServiceList setId(String id) {
        this.id = id;
        return this;
    }

    public ServiceList setImg(String img) {
        this.img = img;
        return this;
    }

    public ServiceList setColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ServiceList.class.getSimpleName() + "[", "]")
                .add("servType='" + servType + "'")
                .add("label='" + label + "'")
                .add("id='" + id + "'")
                .add("img='" + img + "'")
                .add("color='" + color + "'")
                .toString();
    }
}
