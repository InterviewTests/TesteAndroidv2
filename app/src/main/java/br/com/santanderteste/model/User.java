package br.com.santanderteste.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
@Entity
public class User implements Parcelable {

    @PrimaryKey
    @SerializedName("userId")
    private int userId;
    @SerializedName("name")
    private String name;
    @SerializedName("bankAccount")
    private String bankAccount;
    @SerializedName("agency")
    private String agency;
    @SerializedName("balance")
    private double balance;

    public User(int userId, String name, String bankAccount, String agency, double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.balance = balance;
        this.agency = agency;
    }

    public User(Parcel in) {
        this.userId = in.readInt();
        this.name = in.readString();
        this.bankAccount = in.readString();
        this.agency = in.readString();
        this.balance = in.readDouble();
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.userId);
        parcel.writeString(this.name);
        parcel.writeString(this.bankAccount);
        parcel.writeString(this.agency);
        parcel.writeDouble(this.balance);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
