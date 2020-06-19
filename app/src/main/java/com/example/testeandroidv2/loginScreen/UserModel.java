package com.example.testeandroidv2.loginScreen;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserModel {
    int userId;
    String name;
    String bankAccount;
    String agency;
    double balance;

    public UserModel(int userId, String name, String bankAccount, String agency, double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    @NonNull
    @Override
    public String toString() {
        return "{\n\tuserId: "+userId+",\n\tname: "+name+",\n\tbankAccount: "+bankAccount+",\n\tagency: "+agency+",\n\tbalance: "+balance+"\n}";
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

    public String getBankAccount() {
        return bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public double getBalance() {
        return balance;
    }
}

class UserViewModel implements Parcelable {
    private int userId;
    String name;
    String bankAccount;
    String agency;
    double balance;

    public UserViewModel(){ }

    protected UserViewModel(Parcel in) {
        userId = in.readInt();
        name = in.readString();
        bankAccount = in.readString();
        agency = in.readString();
        balance = in.readDouble();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeString(name);
        parcel.writeString(bankAccount);
        parcel.writeString(agency);
        parcel.writeDouble(balance);
    }
}