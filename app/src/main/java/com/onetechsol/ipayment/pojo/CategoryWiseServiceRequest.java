package com.onetechsol.ipayment.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryWiseServiceRequest {

    @SerializedName("Version")
    @Expose
    private String version;

    @SerializedName("category_id")
    @Expose
    private String categoryId;

    @SerializedName("category_name")
    @Expose
    private String categoryName;


    public CategoryWiseServiceRequest(String version, String categoryId, String categoryName) {
        this.version = version;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String version() {
        return version;
    }

    public String categoryId() {
        return categoryId;
    }

    public String categoryName() {
        return categoryName;
    }
}
