package com.example.androidcodingtest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statement implements Parcelable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Parcelable.Creator<Statement> CREATOR = new Creator<Statement>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Statement createFromParcel(Parcel in) {
            return new Statement(in);
        }

        public Statement[] newArray(int size) {
            return (new Statement[size]);
        }

    }
            ;

    protected Statement(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.desc = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Statement() {
    }

    /**
     *
     * @param title
     * @param desc
     * @param value
     * @param date
     */
    public Statement(String title, String desc, String date, double value) {
        super();
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(desc);
        dest.writeValue(date);
        dest.writeValue(value);
    }

    public int describeContents() {
        return 0;
    }

}