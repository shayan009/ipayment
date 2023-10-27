package com.onetechsol.ipayment.pojo;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.StringJoiner;

public class GetKitListResponse extends Response {

    @SerializedName("data")
    @Expose
    GetKitListResponseData data;

    public GetKitListResponseData data() {
        return data;
    }

    @NonNull
    @Override
    public String toString() {
        return new StringJoiner(", ", GetKitListResponse.class.getSimpleName() + "[", "]")
                .add("data=" + data)
                .add("status='" + status + "'")
                .add("message='" + message + "'")
                .toString();
    }
}
