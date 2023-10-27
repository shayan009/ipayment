package com.onetechsol.ipayment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDepartmentListResponseData {

    @SerializedName("department_list")
    @Expose
    public List<DepartmentModel> departmentList;


    public List<DepartmentModel> departmentList() {
        return departmentList;
    }
}
