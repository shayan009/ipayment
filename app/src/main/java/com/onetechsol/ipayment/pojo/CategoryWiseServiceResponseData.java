package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryWiseServiceResponseData {

    @SerializedName("master_version")
    @Expose
    public String masterVersion;

    @SerializedName("company_version")
    @Expose
    public String companyVersion;

    @SerializedName("service_list")
    @Expose
    public List<ServiceList> serviceCategoryModels;


    @SerializedName("service2_list")
    @Expose
    public List<ServiceList> getServiceCategoryModels2;


    public List<ServiceList> getServiceCategoryModels2() {
        return getServiceCategoryModels2;
    }


    public String masterVersion() {
        return masterVersion;
    }

    public CategoryWiseServiceResponseData setMasterVersion(String masterVersion) {
        this.masterVersion = masterVersion;
        return this;
    }

    public String companyVersion() {
        return companyVersion;
    }

    public CategoryWiseServiceResponseData setCompanyVersion(String companyVersion) {
        this.companyVersion = companyVersion;
        return this;
    }

    public List<ServiceList> serviceCategoryModels() {
        return serviceCategoryModels;
    }

    public CategoryWiseServiceResponseData setServiceCategoryModels(List<ServiceList> serviceCategoryModels) {
        this.serviceCategoryModels = serviceCategoryModels;
        return this;
    }
}
