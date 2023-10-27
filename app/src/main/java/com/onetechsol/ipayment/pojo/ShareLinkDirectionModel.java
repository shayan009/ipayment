package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class ShareLinkDirectionModel implements Parcelable {

    public static final Creator<ShareLinkDirectionModel> CREATOR = new Creator<ShareLinkDirectionModel>() {
        @Override
        public ShareLinkDirectionModel createFromParcel(Parcel in) {
            return new ShareLinkDirectionModel(in);
        }

        @Override
        public ShareLinkDirectionModel[] newArray(int size) {
            return new ShareLinkDirectionModel[size];
        }
    };
    public String id;
    public String name;

    public ShareLinkDirectionModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    protected ShareLinkDirectionModel(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }
}
