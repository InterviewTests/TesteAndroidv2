package com.casasw.bankapp;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class LoginModel {
    private String user;
    private String password;

    LoginModel(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}

class LoginViewModel implements Parcelable {
    //filter to have only the needed data
    private int userId;
    private String name;
    private int bankAccount;
    private int agency;
    private double balance;

    LoginViewModel(int userId, String name, int backAccount, int agency, double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = backAccount;
        this.agency = agency;
        this.balance = balance;
    }

    private LoginViewModel(Parcel in) {
        userId = in.readInt();
        name = in.readString();
        bankAccount = in.readInt();
        agency = in.readInt();
        balance = in.readDouble();
    }

    public static final Creator<LoginViewModel> CREATOR = new Creator<LoginViewModel>() {
        @Override
        public LoginViewModel createFromParcel(Parcel source) {
            return new LoginViewModel(source);
        }

        @Override
        public LoginViewModel[] newArray(int size) {
            return new LoginViewModel[0];
        }
    };

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getBankAccount() {
        return bankAccount;
    }

    public int getAgency() {
        return agency;
    }

    public double getBalance() {
        return balance;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(name);
        dest.writeInt(bankAccount);
        dest.writeInt(agency);
        dest.writeDouble(balance);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

class LoginRequest {
    private LoginModel login;
    private Context context;

     LoginRequest(LoginModel login, Context context) {
        this.login = login;
        this.context = context;
    }

    public LoginModel getLogin() {
        return login;
    }

    Context getContext() {
        return context;
    }
}

class LoginResponse {
    private String loginJSON;

    public String getLoginJSON() {
        return loginJSON;
    }

    void setLoginJSON(String loginJSON) {
        this.loginJSON = loginJSON;
    }
}
