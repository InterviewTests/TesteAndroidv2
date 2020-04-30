package com.br.example.fakebank.infrastructure.retrofit.entities;

import java.text.DecimalFormat;

public class StatementEntity {
    private String title;
    private String desc;
    private String date;
    private Double value;

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

    public String formatValue(){
        DecimalFormat precision = new DecimalFormat("#,##0.00");
        return precision.format(this.value);
    }

    public String formatDate(){
        String[] date = this.date.split("-");
        return date[2]+"/"+date[1]+"/"+date[0];
    }
}
