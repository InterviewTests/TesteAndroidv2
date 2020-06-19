package com.example.testeandroidv2.statementScreen;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.testeandroidv2.loginScreen.UserModel;

public class ClientModel {
    String name;
    String account;
    String balance;

    public ClientModel(){
        super();
    }

    public ClientModel(String name, String account, String balance) {
        this.name = name;
        this.account = account;
        this.balance = balance;
    }

    @NonNull
    @Override
    public String toString() {
        return "{\n\tname: "+name+",\n\taccount: "+account+",\n\tbalance: "+balance+"\n}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}

class ClientViewModel implements Parcelable {
    String name;
    String account;
    String balance;

    protected ClientViewModel(Parcel in) {
        name = in.readString();
        account = in.readString();
        balance = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(account);
        dest.writeString(balance);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ClientViewModel> CREATOR = new Creator<ClientViewModel>() {
        @Override
        public ClientViewModel createFromParcel(Parcel in) {
            return new ClientViewModel(in);
        }

        @Override
        public ClientViewModel[] newArray(int size) {
            return new ClientViewModel[size];
        }
    };
}

class ClientRequest {
    UserModel userModel;
}

class ClientResponse{
    UserModel userModel;
    Object error;
}