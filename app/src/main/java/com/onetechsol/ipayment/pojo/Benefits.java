package com.onetechsol.ipayment.pojo;

import java.util.StringJoiner;

public class Benefits {

    private int id;
    private String benefitTitle;
    private String benefitDesc;
    private String img;

    public Benefits(int id, String benefitTitle, String benefitDesc, String img) {
        this.id = id;
        this.benefitTitle = benefitTitle;
        this.benefitDesc = benefitDesc;
        this.img = img;
    }

    public int id() {
        return id;
    }

    public String benefitTitle() {
        return benefitTitle;
    }

    public String benefitDesc() {
        return benefitDesc;
    }

    public String img() {
        return img;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Benefits.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("benefitTitle='" + benefitTitle + "'")
                .add("benefitDesc='" + benefitDesc + "'")
                .add("img='" + img + "'")
                .toString();
    }
}
