package com.example.rossinyamaral.bank.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rossinyamaral on 08/12/18.
 */

public class UserAccountModel implements Parcelable {

    public int userId;
    public String name;
    public String bankAccount;
    public String agency;
    public double balance;

    public UserAccountModel() {}

    protected UserAccountModel(Parcel in) {
        userId = in.readInt();
        name = in.readString();
        bankAccount = in.readString();
        agency = in.readString();
        balance = in.readDouble();
    }


    public static final Creator<UserAccountModel> CREATOR = new Creator<UserAccountModel>() {
        @Override
        public UserAccountModel createFromParcel(Parcel in) {
            return new UserAccountModel(in);
        }

        @Override
        public UserAccountModel[] newArray(int size) {
            return new UserAccountModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(name);
        dest.writeString(bankAccount);
        dest.writeString(agency);
        dest.writeDouble(balance);
    }
}
