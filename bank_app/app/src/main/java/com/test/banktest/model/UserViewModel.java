package com.test.banktest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserViewModel implements Parcelable {

    public String name;
    public String agencyAccount;
    public String balance;

    public UserViewModel(){

    }

    protected UserViewModel(Parcel in) {
        name = in.readString();
        agencyAccount = in.readString();
        balance = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(agencyAccount);
        dest.writeString(balance);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserViewModel> CREATOR = new Creator<UserViewModel>() {
        @Override
        public UserViewModel createFromParcel(Parcel in) {
            return new UserViewModel(in);
        }

        @Override
        public UserViewModel[] newArray(int size) {
            return new UserViewModel[size];
        }
    };
}
