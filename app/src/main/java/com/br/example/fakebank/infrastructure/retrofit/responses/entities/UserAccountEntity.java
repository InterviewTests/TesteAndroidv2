package com.br.example.fakebank.infrastructure.retrofit.responses.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class UserAccountEntity implements Parcelable {
    private Integer userId;
    private String name;
    private String bankAccount;
    private String agency;
    private Double balance;

    public UserAccountEntity(Parcel in) {
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        name = in.readString();
        bankAccount = in.readString();
        agency = in.readString();
        if (in.readByte() == 0) {
            balance = null;
        } else {
            balance = in.readDouble();
        }
    }

    public static final Creator<UserAccountEntity> CREATOR = new Creator<UserAccountEntity>() {
        @Override
        public UserAccountEntity createFromParcel(Parcel in) {
            return new UserAccountEntity(in);
        }

        @Override
        public UserAccountEntity[] newArray(int size) {
            return new UserAccountEntity[size];
        }
    };

    public UserAccountEntity() {

    }

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
            parcel.writeInt(userId);
        }
        parcel.writeString(name);
        parcel.writeString(bankAccount);
        parcel.writeString(agency);
        if (balance == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(balance);
        }
    }
}
