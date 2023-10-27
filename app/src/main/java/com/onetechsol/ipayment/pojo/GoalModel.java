package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoalModel implements Parcelable {


    public static final Creator<GoalModel> CREATOR = new Creator<GoalModel>() {
        @Override
        public GoalModel createFromParcel(Parcel in) {
            return new GoalModel(in);
        }

        @Override
        public GoalModel[] newArray(int size) {
            return new GoalModel[size];
        }
    };
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("product_id")
    @Expose
    public String productId;
    @SerializedName("earning")
    @Expose
    public double earning;
    @SerializedName("icon")
    @Expose
    public String image;

    public GoalModel(String name, String productId, double earning, String image) {
        this.name = name;
        this.productId = productId;
        this.earning = earning;
        this.image = image;
    }

    protected GoalModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        productId = in.readString();
        earning = in.readDouble();
        image = in.readString();
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String productId() {
        return productId;
    }

    public double earning() {
        return earning;
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
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(productId);
        parcel.writeDouble(earning);
        parcel.writeString(image);
    }
}
