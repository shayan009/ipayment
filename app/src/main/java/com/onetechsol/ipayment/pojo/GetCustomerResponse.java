package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Transient;

import java.util.List;

public class GetCustomerResponse {


    @SerializedName("data")
    @Expose
    private GetCustomerResponseData getCustomerResponseData;

    @Transient
    private List<MyCustCategoryItem> myCustCategoryItemList;

    @Transient
    private List<MyCustomerDetail> myCustomerDetails;

    public List<MyCustomerDetail> myCustomerDetails() {
        return myCustomerDetails;
    }

    public GetCustomerResponse setMyCustomerDetails(List<MyCustomerDetail> myCustomerDetails) {
        this.myCustomerDetails = myCustomerDetails;
        return this;
    }

    public GetCustomerResponseData getGetCustomerResponseData() {
        return getCustomerResponseData;
    }

    public List<MyCustCategoryItem> getMyCustCategoryItemList() {
        return myCustCategoryItemList;
    }

    public GetCustomerResponse setMyCustCategoryItemList(List<MyCustCategoryItem> myCustCategoryItemList) {
        this.myCustCategoryItemList = myCustCategoryItemList;
        return this;
    }
}
