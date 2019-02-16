package com.santander.ian.santanderauth.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class User implements Parcelable{

    private long userid;
    private String name;
    private long bankaccount;
    private long agency;
    private BigDecimal balance;

    public User(long userid, String name, long bankaccount, long agency, BigDecimal balance) {
        this.userid = userid;
        this.name = name;
        this.bankaccount = bankaccount;
        this.agency = agency;
        this.balance = balance;
    }

    public User() {
        this.userid = -1;
        this.name = "-1";
        this.bankaccount = -1;
        this.agency = -1;
        this.balance = new BigDecimal(-1);
    }

    public User(Parcel parcel){
        userid = parcel.readLong();
        name = parcel.readString();
        bankaccount = parcel.readLong();
        agency = parcel.readLong();
        balance = (BigDecimal) parcel.readSerializable();
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(long bankaccount) {
        this.bankaccount = bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        try {
            this.bankaccount = Long.valueOf(bankaccount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.bankaccount = -1;
        }
    }

    public long getAgency() {
        return agency;
    }

    public void setAgency(long agency) {
        this.agency = agency;
    }

    public void setAgency(String agency) {
        try {
            this.agency = Long.valueOf(agency);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.agency = -1;
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setBalance(String balance) {
        try {
            this.balance = new BigDecimal(balance.replaceAll(",", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.balance = new BigDecimal(-1);
        }
    }


    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(userid);
        parcel.writeString(name);
        parcel.writeLong(bankaccount);
        parcel.writeLong(agency);
        parcel.writeSerializable(balance);

    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>(){

        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };


}
