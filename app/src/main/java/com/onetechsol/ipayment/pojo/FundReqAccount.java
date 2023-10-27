package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.StringJoiner;

public class FundReqAccount implements Parcelable {

    public static final Creator<FundReqAccount> CREATOR = new Creator<FundReqAccount>() {
        @Override
        public FundReqAccount createFromParcel(Parcel in) {
            return new FundReqAccount(in);
        }

        @Override
        public FundReqAccount[] newArray(int size) {
            return new FundReqAccount[size];
        }
    };
    private String id;
    private String label;
    private String title;
    private String name;
    private String img;
    private String account;
    private String ifsc;

    protected FundReqAccount(Parcel in) {
        id = in.readString();
        label = in.readString();
        title = in.readString();
        name = in.readString();
        img = in.readString();
        account = in.readString();
        ifsc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(label);
        dest.writeString(title);
        dest.writeString(name);
        dest.writeString(img);
        dest.writeString(account);
        dest.writeString(ifsc);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FundReqAccount.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("label='" + label + "'")
                .add("title='" + title + "'")
                .add("name='" + name + "'")
                .add("img='" + img + "'")
                .add("account='" + account + "'")
                .add("ifsc='" + ifsc + "'")
                .toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String account() {
        return account;
    }

    public String ifsc() {
        return ifsc;
    }

    public String id() {
        return id;
    }

    public String label() {
        return label;
    }

    public String title() {
        return title;
    }

    public String name() {
        return name;
    }

    public String img() {
        return img;
    }
}
