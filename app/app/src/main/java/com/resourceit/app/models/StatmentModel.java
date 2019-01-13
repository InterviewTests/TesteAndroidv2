package com.resourceit.app.models;

import java.util.List;

public class StatmentModel {

    private List<StatmentModel > statmentModel;
    private String title;
    private String desc;
    private String date;
    private double value;

    public List<StatmentModel> getStatmentModel() {
        return statmentModel;
    }

    public void setStatmentModel(List<StatmentModel> statmentModel) {
        this.statmentModel = statmentModel;
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
}
