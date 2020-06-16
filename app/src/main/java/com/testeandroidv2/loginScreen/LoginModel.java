package com.testeandroidv2.loginScreen;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

public class LoginModel {
    private int userId;
    String name;
    int bankAccount;
    String agency;
    double balance;

    LoginModel(int userId, String name, int bankAccount, String agency, double balance) {
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
}

class LoginViewModel implements Parcelable {
    String name;
    int bankAccount;
    String agency;
    double balance;

    LoginViewModel(){}

    private LoginViewModel(Parcel in) {
        name = in.readString();
        bankAccount = in.readInt();
        agency = in.readString();
        balance = in.readDouble();
    }

    public static final Creator<LoginViewModel> CREATOR = new Creator<LoginViewModel>() {
        @Override
        public LoginViewModel createFromParcel(Parcel in) {
            return new LoginViewModel(in);
        }

        @Override
        public LoginViewModel[] newArray(int size) {
            return new LoginViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(bankAccount);
        parcel.writeString(agency);
        parcel.writeDouble(balance);
    }
}

class LoginRequest {
    String user;
    String password;
}

class LoginResponse {
    LoginModel userAccount;
    Object error;
}
