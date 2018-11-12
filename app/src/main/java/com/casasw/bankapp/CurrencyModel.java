package com.casasw.bankapp;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

class CurrencyModel {
    private String title, desc, date;
    private double balance;

    CurrencyModel(String title, String desc, String date, double balance) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.balance = balance;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public double getBalance() {
        return balance;
    }
}

class CurrencyViewModel implements Parcelable {
    //filter to have only the needed data
    private ArrayList<CurrencyModel> listOfStatements;

    public CurrencyViewModel(ArrayList<CurrencyModel> listOfStatements) {
        this.listOfStatements = listOfStatements;
    }

    private CurrencyViewModel (Parcel in) {
        listOfStatements = in.readArrayList(CurrencyViewModel.class.getClassLoader());
    }

    public static final Creator<CurrencyViewModel> CREATOR = new Creator<CurrencyViewModel>() {
        @Override
        public CurrencyViewModel createFromParcel(Parcel source) {
            return new CurrencyViewModel(source);
        }

        @Override
        public CurrencyViewModel[] newArray(int size) {
            return new CurrencyViewModel[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public ArrayList<CurrencyModel> getListOfStatements() {
        return listOfStatements;
    }
}

class CurrencyRequest {
    private LoginViewModel mLoginViewModel;
    private Context mContext;
    CurrencyRequest(LoginViewModel mLoginViewModel, Context mContext) {
        this.mLoginViewModel = mLoginViewModel;
        this.mContext = mContext;

    }

    public LoginViewModel getMLoginViewModel() {
        return mLoginViewModel;
    }

    public Context getmContext() {
        return mContext;
    }
}

class CurrencyResponse {
    String currencyJSON;

    public String getCurrencyJSON() {
        return currencyJSON;
    }

    public void setCurrencyJSON(String currencyJSON) {
        this.currencyJSON = currencyJSON;
    }
}
