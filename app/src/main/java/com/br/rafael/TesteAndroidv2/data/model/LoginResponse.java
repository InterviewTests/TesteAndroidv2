package com.br.rafael.TesteAndroidv2.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable {

    @SerializedName("userAccount")
    private Login login;

    @SerializedName("error")
    private Error error;

    public LoginResponse() {

    }

    protected LoginResponse(Parcel in) {
        login = in.readParcelable(Login.class.getClassLoader());
        error = in.readParcelable(Error.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(login, flags);
        dest.writeParcelable(error, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    public Login getLogin() {
        return login;
    }

    public Error getError() {
        return error;
    }



}
