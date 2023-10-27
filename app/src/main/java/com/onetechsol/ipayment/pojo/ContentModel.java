package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class ContentModel implements Parcelable {

    public static final Creator<ContentModel> CREATOR = new Creator<ContentModel>() {
        @Override
        public ContentModel createFromParcel(Parcel in) {
            return new ContentModel(in);
        }

        @Override
        public ContentModel[] newArray(int size) {
            return new ContentModel[size];
        }
    };
    private String id;
    private int type;
    private String url;

    public ContentModel(String id, int type, String url) {
        this.id = id;
        this.type = type;
        this.url = url;
    }

    protected ContentModel(Parcel in) {
        id = in.readString();
        type = in.readInt();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(type);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String id() {
        return id;
    }

    public int type() {
        return type;
    }

    public String url() {
        return url;
    }
}
