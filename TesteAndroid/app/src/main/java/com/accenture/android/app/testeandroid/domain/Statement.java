package com.accenture.android.app.testeandroid.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class Statement implements Parcelable {
    private String title;
    private String desc;
    private String date;
    private Double value;

    public Statement() {
    }

    protected Statement(Parcel in) {
        title = in.readString();
        desc = in.readString();
        date = in.readString();
        if (in.readByte() == 0) {
            value = null;
        } else {
            value = in.readDouble();
        }
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(date);
        if (value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(value);
        }
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                ", value=" + value +
                '}';
    }
    public Calendar getDateCalendar() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        Date date = format.parse(this.date);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar;
    }

}
