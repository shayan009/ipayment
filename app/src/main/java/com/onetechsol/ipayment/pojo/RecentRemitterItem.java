package com.onetechsol.ipayment.pojo;

public class RecentRemitterItem {

    private String id;

    private String remMobile;

    private String remName;

    private String time;

    public RecentRemitterItem(String id, String remMobile, String remName, String time) {
        this.id = id;
        this.remMobile = remMobile;
        this.remName = remName;
        this.time = time;
    }

    public String id() {
        return id;
    }

    public String remMobile() {
        return remMobile;
    }

    public String remName() {
        return remName;
    }

    public String time() {
        return time;
    }
}
