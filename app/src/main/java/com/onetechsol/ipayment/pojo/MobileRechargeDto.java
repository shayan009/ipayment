package com.onetechsol.ipayment.pojo;

public class MobileRechargeDto {

    private String title;
    private String editFieldHintMob;

    private int editFieldMobLength;

    public MobileRechargeDto(String title, String editFieldHintMob, int editFieldMobLength) {
        this.title = title;
        this.editFieldHintMob = editFieldHintMob;
        this.editFieldMobLength = editFieldMobLength;
    }

    public int editFieldMobLength() {
        return editFieldMobLength;
    }

    public String title() {
        return title;
    }

    public String editFieldHintMob() {
        return editFieldHintMob;
    }
}
