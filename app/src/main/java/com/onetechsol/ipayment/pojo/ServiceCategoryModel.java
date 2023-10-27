package com.onetechsol.ipayment.pojo;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.StringJoiner;

public class ServiceCategoryModel implements Parcelable {

    public static final Creator<ServiceCategoryModel> CREATOR = new Creator<ServiceCategoryModel>() {
        @Override
        public ServiceCategoryModel createFromParcel(Parcel in) {
            return new ServiceCategoryModel(in);
        }

        @Override
        public ServiceCategoryModel[] newArray(int size) {
            return new ServiceCategoryModel[size];
        }
    };
    private int id;
    private String categoryId;
    private String title;
    private String imagePath;
    private ServiceCategoryType serviceType;
    private String isRedirectUrl;
    private boolean kycNeeded;

    public ServiceCategoryModel() {
    }


    public ServiceCategoryModel(int id, String categoryId, String title, String imagePath, ServiceCategoryType serviceType, String isRedirectUrl, boolean kycNeeded) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.imagePath = imagePath;
        this.serviceType = serviceType;
        this.isRedirectUrl = isRedirectUrl;
        this.kycNeeded = kycNeeded;
    }

    protected ServiceCategoryModel(Parcel in) {
        id = in.readInt();
        categoryId = in.readString();
        title = in.readString();
        imagePath = in.readString();
        isRedirectUrl = in.readString();
        serviceType = (ServiceCategoryType) in.readSerializable();
        kycNeeded = in.readByte() != 0;
    }

    public int id() {
        return id;
    }

    public ServiceCategoryModel setId(int id) {
        this.id = id;
        return this;
    }

    public String categoryId() {
        return categoryId;
    }

    public ServiceCategoryModel setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String title() {
        return title;
    }

    public ServiceCategoryModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String imagePath() {
        return imagePath;
    }

    public ServiceCategoryModel setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public ServiceCategoryType serviceType() {
        return serviceType;
    }

    public ServiceCategoryModel setServiceType(ServiceCategoryType serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public String isRedirectUrl() {
        return isRedirectUrl;
    }

    public ServiceCategoryModel setIsRedirectUrl(String isRedirectUrl) {
        this.isRedirectUrl = isRedirectUrl;
        return this;
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
        parcel.writeSerializable(serviceType);
        parcel.writeByte((byte) (kycNeeded ? 1 : 0));

    }

    public boolean kycNeeded() {
        return kycNeeded;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ServiceCategoryModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("categoryId='" + categoryId + "'")
                .add("title='" + title + "'")
                .add("imagePath='" + imagePath + "'")
                .add("serviceType=" + serviceType)
                .add("isRedirectUrl='" + isRedirectUrl + "'")
                .add("kycNeeded=" + kycNeeded)
                .toString();
    }
}
