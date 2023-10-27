package com.onetechsol.ipayment.pojo;

public class SettingItem {

    private int id;

    private String title;

    private String desc;

    private String url;

    public SettingItem(int id, String title, String desc, String url) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.url = url;
    }

    public int id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String desc() {
        return desc;
    }

    public String url() {
        return url;
    }
}
