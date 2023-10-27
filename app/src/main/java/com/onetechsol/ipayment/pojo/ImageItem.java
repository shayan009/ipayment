package com.onetechsol.ipayment.pojo;

public class ImageItem {

    private int id;
    private String imgUrl;

    public ImageItem() {
    }

    public ImageItem(int id, String imgUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String imgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
