package com.home.project.testeandroidv2.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BankStatement implements Serializable {

    private String title;
    private String date;
    private String desc;
    private String value;

    public BankStatement() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
