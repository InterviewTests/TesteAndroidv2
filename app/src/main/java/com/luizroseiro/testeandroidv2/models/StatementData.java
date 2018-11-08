package com.luizroseiro.testeandroidv2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatementData {

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

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getFormattedDate() {
        String[] dateValues = date.split("-");
        return dateValues[2] + "/" + dateValues[1] + "/" + dateValues[2];
    }

    public double getValue() {
        return value;
    }

}
