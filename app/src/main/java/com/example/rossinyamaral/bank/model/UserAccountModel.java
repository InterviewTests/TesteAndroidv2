package com.example.rossinyamaral.bank.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rossinyamaral on 08/12/18.
 */

public class UserAccountModel implements Parcelable {

    int userId;
    String name;
    String bankAccount;
    String agency;
    double balance;

    public UserAccountModel() {}

    private UserAccountModel(Parcel in) {
        userId = in.readInt();
        name = in.readString();
        bankAccount = in.readString();
        agency = in.readString();
        balance = in.readDouble();
    }


    public static final Parcelable.Creator<UserAccountModel> CREATOR = new Parcelable.Creator<UserAccountModel>() {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
