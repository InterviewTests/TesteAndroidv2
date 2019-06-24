package com.bilulo.androidtest04.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class UserAccountModel implements Parcelable {
    @SerializedName("userId")
    public Integer userId;
    @SerializedName("name")
    public String name;
    @SerializedName("bankAccount")
    public String bankAccount;
    @SerializedName("agency")
    public String agency;
    @SerializedName("balance")
    public BigDecimal balance;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.userId);
        dest.writeString(this.name);
        dest.writeString(this.bankAccount);
        dest.writeString(this.agency);
        dest.writeSerializable(this.balance);
    }

    public UserAccountModel() {
    }

    protected UserAccountModel(Parcel in) {
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.bankAccount = in.readString();
        this.agency = in.readString();
        this.balance = (BigDecimal) in.readSerializable();
    }

    public static final Parcelable.Creator<UserAccountModel> CREATOR = new Parcelable.Creator<UserAccountModel>() {
        @Override
        public UserAccountModel createFromParcel(Parcel source) {
            return new UserAccountModel(source);
        }

        @Override
        public UserAccountModel[] newArray(int size) {
            return new UserAccountModel[size];
        }
    };
}
