package com.onetechsol.ipayment.pojo;

import java.util.StringJoiner;

import kotlin.jvm.Transient;

public class SideSheetItem {

    public String name;

    public String value;

    @Transient
    public boolean odd;

    @Transient
    public int id;

    @Transient
    public boolean selected = false;


    public SideSheetItem(String name, boolean odd, int id, boolean selected) {
        this.name = name;
        this.odd = odd;
        this.id = id;
        this.selected = selected;
    }

    public SideSheetItem(String name, String value, boolean odd, int id, boolean selected) {
        this.name = name;
        this.value = value;
        this.odd = odd;
        this.id = id;
        this.selected = selected;
    }

    public String value() {
        return value;
    }

    public SideSheetItem setValue(String value) {
        this.value = value;
        return this;
    }

    public String name() {
        return name;
    }

    public SideSheetItem setName(String name) {
        this.name = name;
        return this;
    }

    public boolean odd() {
        return odd;
    }

    public SideSheetItem setOdd(boolean odd) {
        this.odd = odd;
        return this;
    }

    public int id() {
        return id;
    }

    public SideSheetItem setId(int id) {
        this.id = id;
        return this;
    }

    public boolean selected() {
        return selected;
    }

    public SideSheetItem setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SideSheetItem.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("value='" + value + "'")
                .add("odd=" + odd)
                .add("id=" + id)
                .add("selected=" + selected)
                .toString();
    }
}
