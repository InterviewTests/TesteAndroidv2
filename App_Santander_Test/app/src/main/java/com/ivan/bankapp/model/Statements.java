package com.ivan.bankapp.model;

import com.google.gson.annotations.SerializedName;

public class Statements {

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String desc;

    @SerializedName("date")
    private String date;

    @SerializedName("value")
    private Double value;

    public Statements(String tittle, String desc, String date, Double value) {
        this.title = tittle;
        this.desc = desc;
        this.date = date;
        this.value = value;
    }

    public String getTittle() {
        return title;
    }

    public void setTittle(String tittle) {
        this.title = tittle;
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
}
