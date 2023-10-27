package com.onetechsol.ipayment.pojo;

public class PlanSelectionMobileDto {

    private String name;
    private String mobile;
    private String img;
    private String serviceType;

    public PlanSelectionMobileDto(String name, String mobile, String img, String serviceType) {
        this.name = name;
        this.mobile = mobile;
        this.img = img;
        this.serviceType = serviceType;
    }

    public String name() {
        return name;
    }

    public String mobile() {
        return mobile;
    }

    public String img() {
        return img;
    }

    public String serviceType() {
        return serviceType;
    }
}
