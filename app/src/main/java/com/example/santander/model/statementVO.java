package com.example.santander.model;

import com.squareup.moshi.Json;

public class statementVO {

    @Json(name = "title")
    private String title;

    @Json(name = "desc")
    private String desc;

    @Json(name = "date")
    private String date;

    @Json(name = "value")
    private Double value;

    public statementVO(String title, String desc, String date, Double value) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }
}
