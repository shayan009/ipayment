package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Transient;

import java.util.ArrayList;
import java.util.List;

public class DepartmentModel implements Parcelable {

    private String id;
    private String label;
    private String img;

    public boolean selected;
    public String color;

    @SerializedName("category_list")
    @Expose
    private ArrayList<AffiliateModel> affiliateModels;


    @Transient
    private List<SellEarnModel> sellEarnModels;

    protected DepartmentModel(Parcel in) {
        id = in.readString();
        label = in.readString();
        img = in.readString();
        sellEarnModels = in.createTypedArrayList(SellEarnModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(label);
        dest.writeString(img);
        dest.writeTypedList(sellEarnModels);
    }

    public List<SellEarnModel> getSellEarnModels() {
        return sellEarnModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DepartmentModel> CREATOR = new Creator<DepartmentModel>() {
        @Override
        public DepartmentModel createFromParcel(Parcel in) {
            return new DepartmentModel(in);
        }

        @Override
        public DepartmentModel[] newArray(int size) {
            return new DepartmentModel[size];
        }
    };

    public List<SellEarnModel> sellEarnModels() {
        return sellEarnModels;
    }

    public DepartmentModel setSellEarnModels(List<SellEarnModel> sellEarnModels) {
        this.sellEarnModels = sellEarnModels;
        return this;
    }

    public ArrayList<AffiliateModel> affiliateModels() {
        return affiliateModels;
    }

    public String id() {
        return id;
    }

    public String label() {
        return label;
    }

    public String img() {
        return img;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
