package com.onetechsol.ipayment.pojo;

import java.util.StringJoiner;

public class OpCircleItemDto {

    private String id;
    private String title;
    private String image;

    private int type;

    public OpCircleItemDto(String id, String title, String image, int type) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.type = type;
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String image() {
        return image;
    }

    public int type() {
        return type;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OpCircleItemDto.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("title='" + title + "'")
                .add("image='" + image + "'")
                .toString();
    }
}
