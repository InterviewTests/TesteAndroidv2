package com.example.santanderapplication.data.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatementsModel implements Serializable {

    private String title;
    private String desc;
    private Date date;
    private double value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDasc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
