package com.onetechsol.ipayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Error implements Parcelable {
    public static final Creator<Error> CREATOR = new Creator<Error>() {
        @Override
        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        @Override
        public Error[] newArray(int size) {
            return new Error[size];
        }
    };
    @SerializedName("error_internal_code")
    public String errorInternalCode;
    @SerializedName("total_reward_gain")
    public int totalRewardGained;

    protected Error(Parcel in) {
        errorInternalCode = in.readString();
        totalRewardGained = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(errorInternalCode);
        dest.writeInt(totalRewardGained);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
