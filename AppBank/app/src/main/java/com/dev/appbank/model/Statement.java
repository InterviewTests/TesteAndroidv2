package com.dev.appbank.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;

public class Statement implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String desc;
    private String date;
    private double value;

    public Statement() {

    }

    public Statement(String title, String desc, String date, double value) {
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

    @NonNull
    @Override
    public String toString() {
        return "{Title: " + this.title + " Desc: " + this.desc + " Date: " + this.date + " Value: " + this.value +  "}";
    }
}
