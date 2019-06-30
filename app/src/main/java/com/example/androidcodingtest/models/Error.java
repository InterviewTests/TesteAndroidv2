package com.example.androidcodingtest.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error implements Parcelable
{

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<Error> CREATOR = new Creator<Error>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        public Error[] newArray(int size) {
            return (new Error[size]);
        }

    }
            ;

    protected Error(Parcel in) {
        this.code = ((int) in.readValue((int.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Error() {
    }

    /**
     *
     * @param message
     * @param code
     */
    public Error(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}