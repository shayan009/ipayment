package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceListResponseData {


    @SerializedName("master_version")
    @Expose
    private int masterVersion;

    @SerializedName("company_version")
    @Expose
    private int companyVersion;

    @SerializedName("service_list")
    @Expose
    private List<ServiceList> serviceList;


//
//    @SerializedName("others_list")
//    @Expose
//    private List<OthersList> othersLists;

    public ServiceListResponseData() {
    }

    public List<ServiceList> serviceList() {
        return serviceList;
    }

    public ServiceListResponseData setServiceList(List<ServiceList> serviceList) {
        this.serviceList = serviceList;
        return this;
    }

    public int masterVersion() {
        return masterVersion;
    }

    public int companyVersion() {
        return companyVersion;
    }


    public ServiceListResponseData setMasterVersion(int masterVersion) {
        this.masterVersion = masterVersion;
        return this;
    }

    public ServiceListResponseData setCompanyVersion(int companyVersion) {
        this.companyVersion = companyVersion;
        return this;
    }

}
