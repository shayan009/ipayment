package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class AffiliateModel implements Parcelable {


    private String id;
    private String label;
    private String img;

    protected AffiliateModel(Parcel in) {
        id = in.readString();
        label = in.readString();
        img = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(label);
        dest.writeString(img);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AffiliateModel> CREATOR = new Creator<AffiliateModel>() {
        @Override
        public AffiliateModel createFromParcel(Parcel in) {
            return new AffiliateModel(in);
        }

        @Override
        public AffiliateModel[] newArray(int size) {
            return new AffiliateModel[size];
        }
    };

    public String id() {
        return id;
    }

    public String label() {
        return label;
    }

    public String img() {
        return img;
    }
}
