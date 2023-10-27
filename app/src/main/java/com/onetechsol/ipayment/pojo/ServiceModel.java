package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.StringJoiner;

public class ServiceModel implements Parcelable {

    public static final Creator<ServiceModel> CREATOR = new Creator<ServiceModel>() {
        @Override
        public ServiceModel createFromParcel(Parcel in) {
            return new ServiceModel(in);
        }

        @Override
        public ServiceModel[] newArray(int size) {
            return new ServiceModel[size];
        }
    };
    public boolean selected;
    public String color;
    public boolean previous;
    public boolean next;
    private int id;
    private String categoryId;
    private String title;
    private String imagePath;
    private ServiceType serviceType;
    private String isRedirectUrl;

    public ServiceModel() {
    }

    public ServiceModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public ServiceModel(int id, String categoryId, String title, String imagePath, ServiceType serviceType, String isRedirectUrl, boolean selected, String color) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.imagePath = imagePath;
        this.serviceType = serviceType;
        this.isRedirectUrl = isRedirectUrl;
        this.selected = selected;
        this.color = color;
    }

    protected ServiceModel(Parcel in) {
        id = in.readInt();
        categoryId = in.readString();
        title = in.readString();
        imagePath = in.readString();
        isRedirectUrl = in.readString();
        selected = in.readByte() != 0;
        color = in.readString();
    }

    public boolean previous() {
        return previous;
    }

    public ServiceModel setPrevious(boolean previous) {
        this.previous = previous;
        return this;
    }

    public boolean next() {
        return next;
    }

    public ServiceModel setNext(boolean next) {
        this.next = next;
        return this;
    }

    public boolean selected() {
        return selected;
    }

    public String color() {
        return color;
    }

    public ServiceModel setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public ServiceModel setColor(String color) {
        this.color = color;
        return this;
    }

    public String categoryId() {
        return categoryId;
    }

    public int id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String imagePath() {
        return imagePath;
    }

    public ServiceType serviceType() {
        return serviceType;
    }

    public String isRedirectUrl() {
        return isRedirectUrl;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ServiceModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("categoryId='" + categoryId + "'")
                .add("title='" + title + "'")
                .add("imagePath='" + imagePath + "'")
                .add("serviceType=" + serviceType)
                .add("isRedirectUrl='" + isRedirectUrl + "'")
                .toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(categoryId);
        parcel.writeString(title);
        parcel.writeString(imagePath);
        parcel.writeString(isRedirectUrl);
        parcel.writeByte((byte) (selected ? 1 : 0));
        parcel.writeString(color);
    }
}
