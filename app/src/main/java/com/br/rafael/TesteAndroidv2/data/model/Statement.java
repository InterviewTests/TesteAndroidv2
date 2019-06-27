package com.br.rafael.TesteAndroidv2.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Statement implements Parcelable {

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String description;

    @SerializedName("date")
    private String date;

    @SerializedName("value")
    private float value;

    public Statement(){

    }

    protected Statement(Parcel in) {
        title = in.readString();
        description = in.readString();
        date = in.readString();
        value = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeFloat(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Statement> CREATOR = new Creator<Statement>() {
        @Override
        public Statement createFromParcel(Parcel in) {
            return new Statement(in);
        }

        @Override
        public Statement[] newArray(int size) {
            return new Statement[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public float getValue() {
        return value;
    }


}
