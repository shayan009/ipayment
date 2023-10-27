package com.onetechsol.ipayment.pojo;

public class ImageSliderItem {

    private int id;
    private String imageUrl;

    public ImageSliderItem(int id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public ImageSliderItem() {
    }

    public int id() {
        return id;
    }

    public String imageUrl() {
        return imageUrl;
    }
}
