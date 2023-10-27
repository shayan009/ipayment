package com.onetechsol.ipayment.pojo;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Transient;

import java.util.StringJoiner;

public class KitData {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("kit_exist")
    @Expose
    private String kitExist;

    @SerializedName("amt")
    @Expose
    private String amt;

    @SerializedName("kit")
    @Expose
    private String kit;

    @SerializedName("slab")
    @Expose
    private String slab;

    @SerializedName("desc")
    @Expose
    private String desc;

    @SerializedName("label")
    @Expose
    private String label;

    @Transient
    private boolean selected;

    @Transient
    private int index;


    public int index() {
        return index;
    }

    public KitData setIndex(int index) {
        this.index = index;
        return this;
    }

    public boolean selected() {
        return selected;
    }

    public KitData setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public String id() {
        return id;
    }

    public String kitExist() {
        return kitExist;
    }

    public String amt() {
        return amt;
    }

    public String kit() {
        return kit;
    }

    public String slab() {
        return slab;
    }

    public String desc() {
        return desc;
    }

    public String label() {
        return label;
    }

    @NonNull
    @Override
    public String toString() {
        return new StringJoiner(", ", KitData.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("kitExist='" + kitExist + "'")
                .add("amt='" + amt + "'")
                .add("kit='" + kit + "'")
                .add("slab='" + slab + "'")
                .add("desc='" + desc + "'")
                .add("label='" + label + "'")
                .toString();
    }
}
