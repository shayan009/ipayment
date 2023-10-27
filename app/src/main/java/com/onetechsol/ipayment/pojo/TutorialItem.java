package com.onetechsol.ipayment.pojo;

public class TutorialItem {

    private int id;
    private String imageUrl;
    private String title;

    public TutorialItem(int id, String imageUrl, String title) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public TutorialItem() {
    }

    public int id() {
        return id;
    }

    public String imageUrl() {
        return imageUrl;
    }

    public String title() {
        return title;
    }
}
