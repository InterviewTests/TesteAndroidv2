package com.bankapp.loginScreen;

import android.os.Parcel;
import android.os.Parcelable;

public class UserAccount implements Parcelable {

    public long userId;
    public String name;
    public String bankAccount;
    public String agency;
    public String balance;

    public UserAccount() {}

    protected UserAccount(Parcel in) {
        userId = in.readLong();
        name = in.readString();
        bankAccount = in.readString();
        agency = in.readString();
        balance = in.readString();

    }

    public static final Creator<UserAccount> CREATOR = new Creator<UserAccount>() {
        @Override
        public UserAccount createFromParcel(Parcel in) {
            return new UserAccount(in);
        }

        @Override
        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(userId);
        dest.writeString(name);
        dest.writeString(bankAccount);
        dest.writeString(agency);
        dest.writeString(balance);
    }
}
