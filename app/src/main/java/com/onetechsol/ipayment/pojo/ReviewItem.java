package com.onetechsol.ipayment.pojo;

public class ReviewItem {

    private int id;
    private String imageUrl;

    public ReviewItem(int id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public ReviewItem() {
    }

    public int id() {
        return id;
    }

    public String imageUrl() {
        return imageUrl;
    }

}
