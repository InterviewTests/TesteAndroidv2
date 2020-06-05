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

//    protected StatementHeaderModel(Parcel in) {
//        if (in.readByte() == 0) {
//            this.userId = null;
//        } else {
//            this.userId = in.readLong();
//        }
//        this.name = in.readString();
//        this.bankAccount = in.readString();
//        this.agency = in.readString();
//        if (in.readByte() == 0) {
//            this.balance = null;
//        } else {
//            this.balance = in.readDouble();
//        }
//    }
//
//    public static final Creator<StatementHeaderModel> CREATOR = new Creator<StatementHeaderModel>() {
//        @Override
//        public StatementHeaderModel createFromParcel(Parcel in) {
//            return new StatementHeaderModel(in);
//        }
//
//        @Override
//        public StatementHeaderModel[] newArray(int size) {
//            return new StatementHeaderModel[size];
//        }
    };
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getBankAccount() {
//        return bankAccount;
//    }
//
//    public void setBankAccount(String bankAccount) {
//        this.bankAccount = bankAccount;
//    }
//
//    public String getAgency() {
//        return agency;
//    }
//
//    public void setAgency(String agency) {
//        this.agency = agency;
//    }
//
//    public Double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(Double balance) {
//        this.balance = balance;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeLong(this.userId);
//        dest.writeString(this.name);
//        dest.writeString(this.bankAccount);
//        dest.writeString(this.agency);
//        dest.writeDouble(this.balance);
//    }
//}

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


