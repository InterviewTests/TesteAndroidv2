package com.project.personal.app_bank.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statements {
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
    private String value;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }
}
