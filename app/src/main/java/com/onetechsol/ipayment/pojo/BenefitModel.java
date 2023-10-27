package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Transient;

public class BenefitModel implements Parcelable {

    public static final Creator<BenefitModel> CREATOR = new Creator<BenefitModel>() {
        @Override
        public BenefitModel createFromParcel(Parcel in) {
            return new BenefitModel(in);
        }

        @Override
        public BenefitModel[] newArray(int size) {
            return new BenefitModel[size];
        }
    };
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @Transient
    public String description;

    @SerializedName("icon")
    @Expose
    public String image;
    @SerializedName("product_id")
    @Expose
    private String productId;

    public BenefitModel(String id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    protected BenefitModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        image = in.readString();
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
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
        parcel.writeString(description);
        parcel.writeString(image);
    }
}
