package com.br.rafael.TesteAndroidv2.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Error implements Parcelable {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public Error() {

    }

    protected Error(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Error> CREATOR = new Creator<Error>() {
        @Override
        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        @Override
        public Error[] newArray(int size) {
            return new Error[size];
        }
    };

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
