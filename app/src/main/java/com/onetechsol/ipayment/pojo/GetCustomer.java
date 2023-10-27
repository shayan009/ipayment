package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCustomer implements Parcelable {

    public static final Creator<GetCustomer> CREATOR = new Creator<GetCustomer>() {
        @Override
        public GetCustomer createFromParcel(Parcel in) {
            return new GetCustomer(in);
        }

        @Override
        public GetCustomer[] newArray(int size) {
            return new GetCustomer[size];
        }
    };
    @SerializedName("name")
    @Expose
    private String name;
    private String eml;
    private String mob;
    private String pin;
    private String cdtm;
    @SerializedName("form_type")
    @Expose
    private String formType;
    private String referral;
    private String company;
    @SerializedName("service_list")
    private List<GetAffiliateProductDetailData> getAffiliateProductDetailDataList;

    protected GetCustomer(Parcel in) {
        name = in.readString();
        eml = in.readString();
        mob = in.readString();
        pin = in.readString();
        cdtm = in.readString();
        formType = in.readString();
        referral = in.readString();
        company = in.readString();
    }

    public String name() {
        return name;
    }

    public String eml() {
        return eml;
    }

    public String mob() {
        return mob;
    }

    public String pin() {
        return pin;
    }

    public String cdtm() {
        return cdtm;
    }

    public String formType() {
        return formType;
    }

    public String referral() {
        return referral;
    }

    public String company() {
        return company;
    }

    public List<GetAffiliateProductDetailData> getAffiliateProductDetailDataList() {
        return getAffiliateProductDetailDataList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(eml);
        parcel.writeString(mob);
        parcel.writeString(pin);
        parcel.writeString(cdtm);
        parcel.writeString(formType);
        parcel.writeString(referral);
        parcel.writeString(company);
    }
}
