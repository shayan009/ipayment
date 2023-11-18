package com.onetechsol.ipayment.pojo;

public class PayoutFoldingCellModel {

    private String id;
    private String label;

    public PayoutFoldingCellModel(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
