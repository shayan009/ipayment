package com.onetechsol.ipayment.pojo;

public class SupportList {
    public String mob;
    public String eml;
    public String whatsapp;

    public SupportList(String mob, String eml, String whatsapp) {
        this.mob = mob;
        this.eml = eml;
        this.whatsapp = whatsapp;
    }

    public String mob() {
        return mob;
    }

    public String eml() {
        return eml;
    }

    public String whatsapp() {
        return whatsapp;
    }

    @Override
    public String toString() {
        return "SupportList{" +
                "mob='" + mob + '\'' +
                ", eml='" + eml + '\'' +
                ", whatsapp='" + whatsapp + '\'' +
                '}';
    }
}
