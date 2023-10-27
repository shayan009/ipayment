package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceItem {

    @SerializedName("retl_role_id")
    @Expose
    public int retlRoleId;

    @SerializedName("retl_service_status")
    @Expose
    public int retlServiceStatus;

    @SerializedName("api_role_id")
    @Expose
    public int apiRoleId;

    @SerializedName("api_service_status")
    @Expose
    public int apiServiceStatus;

    public int retlRoleId() {
        return retlRoleId;
    }

    public ServiceItem setRetlRoleId(int retlRoleId) {
        this.retlRoleId = retlRoleId;
        return this;
    }

    public int retlServiceStatus() {
        return retlServiceStatus;
    }

    public ServiceItem setRetlServiceStatus(int retlServiceStatus) {
        this.retlServiceStatus = retlServiceStatus;
        return this;
    }

    public int apiRoleId() {
        return apiRoleId;
    }

    public ServiceItem setApiRoleId(int apiRoleId) {
        this.apiRoleId = apiRoleId;
        return this;
    }

    public int apiServiceStatus() {
        return apiServiceStatus;
    }

    public ServiceItem setApiServiceStatus(int apiServiceStatus) {
        this.apiServiceStatus = apiServiceStatus;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServiceItem{");
        sb.append("retlRoleId=").append(retlRoleId);
        sb.append(", retlServiceStatus=").append(retlServiceStatus);
        sb.append(", apiRoleId=").append(apiRoleId);
        sb.append(", apiServiceStatus=").append(apiServiceStatus);
        sb.append('}');
        return sb.toString();
    }
}
