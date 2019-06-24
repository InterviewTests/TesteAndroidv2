package com.bilulo.androidtest04.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ErrorModel implements Parcelable {
    @SerializedName("code")
    private Integer code;
    @SerializedName("message")
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.code);
        dest.writeString(this.message);
    }

    public ErrorModel() {
    }

    protected ErrorModel(Parcel in) {
        this.code = (Integer) in.readValue(Integer.class.getClassLoader());
        this.message = in.readString();
    }

    public static final Parcelable.Creator<ErrorModel> CREATOR = new Parcelable.Creator<ErrorModel>() {
        @Override
        public ErrorModel createFromParcel(Parcel source) {
            return new ErrorModel(source);
        }

        @Override
        public ErrorModel[] newArray(int size) {
            return new ErrorModel[size];
        }
    };
}