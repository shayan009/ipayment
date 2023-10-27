package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TermsConditionsModel implements Parcelable {

    public static final Creator<TermsConditionsModel> CREATOR = new Creator<TermsConditionsModel>() {
        @Override
        public TermsConditionsModel createFromParcel(Parcel in) {
            return new TermsConditionsModel(in);
        }

        @Override
        public TermsConditionsModel[] newArray(int size) {
            return new TermsConditionsModel[size];
        }
    };
    public String id;
    public String name;
    public String image;

    public TermsConditionsModel(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    protected TermsConditionsModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        image = in.readString();
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String image() {
        return image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(image);
    }
}
