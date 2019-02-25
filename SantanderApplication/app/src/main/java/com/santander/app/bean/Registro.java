package com.santander.app.bean;

import java.io.Serializable;
import java.util.Date;

public class Registro implements Serializable {
    private String title;
    private String desc;
    private String date;
    private Float value;

    public Registro() {
    }

    public Registro(String title, String desc, String date, Float value) {
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

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}

