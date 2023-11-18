package com.onetechsol.ipayment.pojo;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.StringJoiner;

public class GetExternalServiceResponse extends Response {


    private GetExternalServiceResponseData data;

    public GetExternalServiceResponseData getData() {
        return data;
    }
}
