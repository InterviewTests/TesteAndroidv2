package br.com.santanderteste.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class Statement implements Parcelable {

    private String title;
    private String desc;
    private String date;
    private double value;

    public Statement(String title, String desc, String date, double value) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
    }

    public Statement(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
        this.date = in.readString();
        this.value = in.readDouble();
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeString(date);
        parcel.writeDouble(value);
    }
}
