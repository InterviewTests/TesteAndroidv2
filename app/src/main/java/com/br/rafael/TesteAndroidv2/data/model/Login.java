package com.br.rafael.TesteAndroidv2.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Login implements Parcelable {

    @SerializedName("userId")
    private Long userId;
    @SerializedName("name")
    private String name;
    @SerializedName("bankAccount")
    private String bankAccount;
    @SerializedName("agency")
    private String agency;

    @SerializedName("balance")
    private float balance;

    public Login() {

    }

    protected Login(Parcel in) {
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readLong();
        }
        name = in.readString();
        bankAccount = in.readString();
        agency = in.readString();
        balance = in.readFloat();
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };

    public Long getUserId() {
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

    public float getBalance() {
        return balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (userId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(userId);
        }
        parcel.writeString(name);
        parcel.writeString(bankAccount);
        parcel.writeString(agency);
        parcel.writeFloat(balance);
    }
}
