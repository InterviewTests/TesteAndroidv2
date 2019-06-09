package br.com.douglas.fukuhara.bank.network.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class UserAccount implements Parcelable {
    @SerializedName("userId")
    int userId;
    @SerializedName("name")
    String name;
    @SerializedName("bankAccount")
    String bankAccount;
    @SerializedName("agency")
    String agency;
    @SerializedName("balance")
    BigDecimal balance;

    protected UserAccount(Parcel in) {
        userId = in.readInt();
        name = in.readString();
        bankAccount = in.readString();
        agency = in.readString();
        balance = new BigDecimal(in.readString());
    }

    public static final Creator<UserAccount> CREATOR = new Creator<UserAccount>() {
        @Override
        public UserAccount createFromParcel(Parcel in) {
            return new UserAccount(in);
        }

        @Override
        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
        }
    };

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

    public BigDecimal getBalance() {
        return balance;
    }

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
        parcel.writeString(balance.toString());
    }
}