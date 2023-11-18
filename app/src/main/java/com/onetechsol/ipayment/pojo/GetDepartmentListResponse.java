package com.onetechsol.ipayment.pojo;

import org.simpleframework.xml.Transient;

import java.util.ArrayList;

public class GetDepartmentListResponse extends Response {

    public GetDepartmentListResponseData data;



    @Transient
    ArrayList<ServiceModel> departmentList = new ArrayList<>();


    public ArrayList<ServiceModel> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(ArrayList<ServiceModel> departmentList) {
        this.departmentList = departmentList;
    }


    public GetDepartmentListResponseData data() {
        return data;
    }
}
