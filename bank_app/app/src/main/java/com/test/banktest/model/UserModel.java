package com.test.banktest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {

    public Integer userId;
    public String name;
    public String bankAccount;
    public String agency;
    public String balance;

    public UserModel(){}

    protected UserModel(Parcel in) {
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        name = in.readString();
        bankAccount = in.readString();
        agency = in.readString();
        balance = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (userId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(userId);
        }
        parcel.writeString(name);
        parcel.writeString(bankAccount);
        parcel.writeString(agency);
        parcel.writeString(balance);
    }
}
