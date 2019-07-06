package com.projeto.testedevandroidsantander.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

class ErrorModel implements Parcelable {
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

    protected ErrorModel(Parcel in) {
        if (in.readByte() == 0) {
            code = null;
        } else {
            code = in.readInt();
        }
        message = in.readString();
    }

    public static final Creator<ErrorModel> CREATOR = new Creator<ErrorModel>() {
        @Override
        public ErrorModel createFromParcel(Parcel in) {
            return new ErrorModel(in);
        }

        @Override
        public ErrorModel[] newArray(int size) {
            return new ErrorModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (code == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(code);
        }
        dest.writeString(message);
    }
}
