package com.riso.zup.bank.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo implements Parcelable {

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("bankAccount")
    @Expose
    private String bankAccount;

    @SerializedName("agency")
    @Expose
    private String agency;

    @SerializedName("balance")
    @Expose
    private double balance;

    protected UserInfo(Parcel in) {
        this.userId = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccount = ((String) in.readValue((String.class.getClassLoader())));
        this.agency = ((String) in.readValue((String.class.getClassLoader())));
        this.balance = ((double) in.readValue((double.class.getClassLoader())));
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(userId);
        parcel.writeValue(name);
        parcel.writeValue(bankAccount);
        parcel.writeValue(agency);
        parcel.writeValue(balance);
    }

    public UserInfo(int userId, String name, String bankAccount, String agency, double balance) {
        super();
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
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
