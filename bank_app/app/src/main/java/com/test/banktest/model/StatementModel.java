package com.test.banktest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StatementModel implements Parcelable {

    public String title;
    public String desc;
    public String date;
    public String value;

    public StatementModel(){}

    protected StatementModel(Parcel in) {
        title = in.readString();
        desc = in.readString();
        date = in.readString();
        value = in.readString();
    }

    public static final Creator<StatementModel> CREATOR = new Creator<StatementModel>() {
        @Override
        public StatementModel createFromParcel(Parcel in) {
            return new StatementModel(in);
        }

        @Override
        public StatementModel[] newArray(int size) {
            return new StatementModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeString(date);
        parcel.writeString(value);
    }
}
