package com.onetechsol.ipayment.pojo;

public class ToastItem {

    String title;

    String subTitle;

    boolean showCancel;

    public ToastItem(String title, String subTitle, boolean showCancel) {
        this.title = title;
        this.subTitle = subTitle;
        this.showCancel = showCancel;
    }

    public String title() {
        return title;
    }

    public ToastItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String subTitle() {
        return subTitle;
    }

    public ToastItem setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public boolean showCancel() {
        return showCancel;
    }

    public ToastItem setShowCancel(boolean showCancel) {
        this.showCancel = showCancel;
        return this;
    }
}
