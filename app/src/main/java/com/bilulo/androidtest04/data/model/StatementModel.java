package com.bilulo.androidtest04.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class StatementModel implements Parcelable {
    @SerializedName("title")
    public String title;
    @SerializedName("desc")
    public String desc;
    @SerializedName("date")
    public String date;
    @SerializedName("value")
    public BigDecimal value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeString(this.date);
        dest.writeSerializable(this.value);
    }

    public StatementModel() {
    }

    protected StatementModel(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
        this.date = in.readString();
        this.value = (BigDecimal) in.readSerializable();
    }

    public static final Parcelable.Creator<StatementModel> CREATOR = new Parcelable.Creator<StatementModel>() {
        @Override
        public StatementModel createFromParcel(Parcel source) {
            return new StatementModel(source);
        }

        @Override
        public StatementModel[] newArray(int size) {
            return new StatementModel[size];
        }
    };
}
