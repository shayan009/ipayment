package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryWiseServiceResponse extends Response {

    @SerializedName("data")
    @Expose
    private CategoryWiseServiceResponseData data;

    public CategoryWiseServiceResponseData data() {
        return data;
    }

    public CategoryWiseServiceResponse setData(CategoryWiseServiceResponseData data) {
        this.data = data;
        return this;
    }
}
