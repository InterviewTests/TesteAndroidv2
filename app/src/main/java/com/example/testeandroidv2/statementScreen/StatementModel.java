package com.example.testeandroidv2.statementScreen;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import androidx.annotation.NonNull;

import com.example.testeandroidv2.loginScreen.UserModel;

public class StatementModel {
    String title;
    String desc;
    String date;
    String value;

    public StatementModel(String title, String desc, String date, String value) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return "{\n\ttitle: "+title+",\n\tdesc: "+desc+",\n\tdate: "+date+",\n\tvalue: "+value+"\n}";
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

class StatementViewModel implements Parcelable {
    String title;
    String desc;
    String date;
    String value;

    protected StatementViewModel(Parcel in) {
        title = in.readString();
        desc = in.readString();
        date = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(date);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StatementViewModel> CREATOR = new Creator<StatementViewModel>() {
        @Override
        public StatementViewModel createFromParcel(Parcel in) {
            return new StatementViewModel(in);
        }

        @Override
        public StatementViewModel[] newArray(int size) {
            return new StatementViewModel[size];
        }
    };
}

class StatementRequest {
    UserModel user;
}

class StatementResponse {
    List<StatementModel> statementList;
    Object error;
}
