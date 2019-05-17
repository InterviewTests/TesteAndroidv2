package com.testeandroidv2.repository.response;

import com.google.gson.annotations.SerializedName;

public class Statement {

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String description;

    @SerializedName("date")
    private String date;

    @SerializedName("value")
    private String value;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }
}
