package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.StringJoiner;

public class SellEarnModel implements Parcelable {


    public static final Creator<SellEarnModel> CREATOR = new Creator<SellEarnModel>() {
        @Override
        public SellEarnModel createFromParcel(Parcel in) {
            return new SellEarnModel(in);
        }

        @Override
        public SellEarnModel[] newArray(int size) {
            return new SellEarnModel[size];
        }
    };
    private String id;
    private String title;
    private double amount;
    private String imagePath;
    private SellEarnType sellEarnType;
    private Integer sellEarnProductId;

    public SellEarnModel(String id, String title, double amount, String imagePath, Integer sellEarnProductId, SellEarnType sellEarnType) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.imagePath = imagePath;
        this.sellEarnProductId = sellEarnProductId;
        this.sellEarnType = sellEarnType;
    }

    protected SellEarnModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        amount = in.readDouble();
        imagePath = in.readString();
        if (in.readByte() == 0) {
            sellEarnProductId = null;
        } else {
            sellEarnProductId = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeDouble(amount);
        dest.writeString(imagePath);
        if (sellEarnProductId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sellEarnProductId);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public SellEarnType sellEarnType() {
        return sellEarnType;
    }

    public Integer sellEarnProductId() {
        return sellEarnProductId;
    }


    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public double amount() {
        return amount;
    }

    public String imagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SellEarnModel.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("title='" + title + "'")
                .add("amount=" + amount)
                .add("imagePath='" + imagePath + "'")
                .add("sellEarnType=" + sellEarnType)
                .add("sellEarnProductId=" + sellEarnProductId)
                .toString();
    }
}
