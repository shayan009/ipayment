package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class InstructionModel implements Parcelable {

    public static final Creator<InstructionModel> CREATOR = new Creator<InstructionModel>() {
        @Override
        public InstructionModel createFromParcel(Parcel in) {
            return new InstructionModel(in);
        }

        @Override
        public InstructionModel[] newArray(int size) {
            return new InstructionModel[size];
        }
    };
    public String id;
    public String name;
    public String image;

    public InstructionModel(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    protected InstructionModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(image);
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

    public String image() {
        return image;
    }


}
