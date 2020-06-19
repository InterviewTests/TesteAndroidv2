package com.example.testeandroidv2.loginScreen;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class LoginModel {
    String login;
    String password;

    public LoginModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "{\n\tlogin: "+login+",\n\tpassword: "+password+"\n}";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class LoginViewModel implements Parcelable {
    String login;
    String password;

    protected LoginViewModel(Parcel in) {
        login = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginViewModel> CREATOR = new Creator<LoginViewModel>() {
        @Override
        public LoginViewModel createFromParcel(Parcel in) {
            return new LoginViewModel(in);
        }

        @Override
        public LoginViewModel[] newArray(int size) {
            return new LoginViewModel[size];
        }
    };
}

class LoginRequest {
    String user;
    String password;
}

class LoginResponse {
    UserModel userAccount;
    Object error;
}
