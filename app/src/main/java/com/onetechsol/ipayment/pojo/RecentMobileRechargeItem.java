package com.onetechsol.ipayment.pojo;

public class RecentMobileRechargeItem {

    private String id;

    private String rechargeMobile;

    private String time;

    public RecentMobileRechargeItem(String id, String rechargeMobile, String time) {
        this.id = id;
        this.rechargeMobile = rechargeMobile;
        this.time = time;
    }

    public String id() {
        return id;
    }

    public String rechargeMobile() {
        return rechargeMobile;
    }

    public String time() {
        return time;
    }
}
