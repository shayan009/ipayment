package com.onetechsol.ipayment.pojo;

import org.simpleframework.xml.Transient;

public class PlanItem {


    private int id;
    private String planName;


    @Transient
    private boolean isSelected;


    public PlanItem(int id, String planName, boolean isSelected) {
        this.id = id;
        this.planName = planName;
        this.isSelected = isSelected;
    }

    public int id() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public PlanItem setSelected(boolean selected) {
        isSelected = selected;
        return this;
    }

    public String planName() {
        return planName;
    }
}
