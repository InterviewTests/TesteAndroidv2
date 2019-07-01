package com.example.androidcodingtest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAccount implements Parcelable
{

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
    public final static Parcelable.Creator<UserAccount> CREATOR = new Creator<UserAccount>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserAccount createFromParcel(Parcel in) {
            return new UserAccount(in);
        }

        public UserAccount[] newArray(int size) {
            return (new UserAccount[size]);
        }

    }
            ;

    protected UserAccount(Parcel in) {
        this.userId = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccount = ((String) in.readValue((String.class.getClassLoader())));
        this.agency = ((String) in.readValue((String.class.getClassLoader())));
        this.balance = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public UserAccount() {
    }

    /**
     *
     * @param balance
     * @param name
     * @param userId
     * @param agency
     * @param bankAccount
     */
    public UserAccount(int userId, String name, String bankAccount, String agency, double balance) {
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

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(name);
        dest.writeValue(bankAccount);
        dest.writeValue(agency);
        dest.writeValue(balance);
    }

    public int describeContents() {
        return 0;
    }

}