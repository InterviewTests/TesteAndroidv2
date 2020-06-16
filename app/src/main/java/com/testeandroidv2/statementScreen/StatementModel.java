package com.testeandroidv2.statementScreen;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class StatementModel {
    private int userId;
    String name;
    int bankAccount;
    String agency;
    double balance;

    StatementModel(int userId, String name, int bankAccount, String agency, double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    @NonNull
    @Override
    public String toString() {
        return "{\n\t userId: "+userId+",\n\tname: "+name+",\n\tbankAccount: "+bankAccount+",\n\tagency: "+agency+",\n\tbalance: "+balance+"\n}";
    }
}

class StatementViewModel implements Parcelable {
    String name;
    int bankAccount;
    String agency;
    double balance;

    public StatementViewModel(){}

    private StatementViewModel(Parcel in) {
        name = in.readString();
        bankAccount = in.readInt();
        agency = in.readString();
        balance = in.readDouble();
    }

    public static final Creator<StatementViewModel> CREATOR = new Creator<StatementViewModel>() {
        @Override
        public StatementViewModel createFromParcel(Parcel in) {
            return new StatementViewModel(in);
        }

        @Override
        public StatementViewModel[] newArray(int size) {
            return new StatementViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(bankAccount);
        parcel.writeString(agency);
        parcel.writeDouble(balance);
    }
}

class StatementRequest {

}

class StatementResponse {
    StatementModel user;
}
