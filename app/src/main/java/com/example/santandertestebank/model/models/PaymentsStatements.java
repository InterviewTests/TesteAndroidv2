package com.example.santandertestebank.model.models;

import com.google.gson.annotations.SerializedName;

public class PaymentsStatements {

    private String title;

    @SerializedName("desc")
    private String description;

    private String date;

    private double value;

    public PaymentsStatements(String title, String description, String date, double value) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
