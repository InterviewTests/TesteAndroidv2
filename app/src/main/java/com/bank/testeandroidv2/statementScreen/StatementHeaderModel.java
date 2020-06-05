package com.bank.testeandroidv2.statementScreen;


import android.os.Parcel;
import android.os.Parcelable;

public class StatementHeaderModel implements Parcelable {
    @Override
    public String toString() {
        return "StatementHeaderModel{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", agency='" + agency + '\'' +
                ", balance=" + balance +
                '}';
    }

    Long userId;
    String name;
    String bankAccount;
    String agency;
    Double balance;

    public StatementHeaderModel(Long userId, String name, String bankAccount, String agency, Double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    protected StatementHeaderModel(Parcel in) {
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readLong();
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

    public static final Creator<StatementHeaderModel> CREATOR = new Creator<StatementHeaderModel>() {
        @Override
        public StatementHeaderModel createFromParcel(Parcel in) {
            return new StatementHeaderModel(in);
        }

        @Override
        public StatementHeaderModel[] newArray(int size) {
            return new StatementHeaderModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(userId);
        }
        dest.writeString(name);
        dest.writeString(bankAccount);
        dest.writeString(agency);
        if (balance == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(balance);
        }
    }
    
    };

class StatementHeaderViewModel {
    String name;
    String bankAccount;
    String balance;
}

class StatementHeaderRequest {
    Long userId;
    String name;
    String bankAccount;
    String agency;
    Double balance;
}

class StatementHeaderResponse {
    Long userId;
    String name;
    String bankAccount;
    String agency;
    Double balance;
}


