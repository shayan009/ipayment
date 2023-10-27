package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import kotlin.jvm.Transient;

public class ServiceListResponse extends Response {

    @Transient
    ArrayList<ServiceModel> serviceModelList;
    @SerializedName("data")
    @Expose
    private ServiceListResponseData data;
    @SerializedName("logout")
    @Expose
    private String logout;


    public ServiceListResponse() {
    }

    public ServiceListResponse(ServiceListResponseData data) {
        this.data = data;
    }

    public ServiceListResponse setData(ServiceListResponseData data) {
        this.data = data;
        return this;
    }

    public String logout() {
        return logout;
    }

    public ServiceListResponse setLogout(String logout) {
        this.logout = logout;
        return this;
    }

    public ArrayList<ServiceModel> serviceModelList() {
        return serviceModelList;
    }

    public ServiceListResponse setServiceModelList(ArrayList<ServiceModel> serviceModelList) {
        this.serviceModelList = serviceModelList;
        return this;
    }

    public ServiceListResponseData data() {
        return data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServiceListResponse{");
        sb.append("data=").append(data);
        sb.append(", status='").append(status).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
