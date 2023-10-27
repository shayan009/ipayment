package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Transient;

import java.util.List;

public class DepartmentModel {

    private String id;
    private String label;
    private String img;

    @SerializedName("category_list")
    @Expose
    private List<AffiliateModel> affiliateModels;


    @Transient
    private List<SellEarnModel> sellEarnModels;

    public List<SellEarnModel> sellEarnModels() {
        return sellEarnModels;
    }

    public DepartmentModel setSellEarnModels(List<SellEarnModel> sellEarnModels) {
        this.sellEarnModels = sellEarnModels;
        return this;
    }

    public List<AffiliateModel> affiliateModels() {
        return affiliateModels;
    }

    public String id() {
        return id;
    }

    public String label() {
        return label;
    }

    public String img() {
        return img;
    }
}
