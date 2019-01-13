package com.resourceit.app.models;

import java.util.List;

public class StatmentModel {

    private List<StatmentModel > statementList;
    private String title;
    private String desc;
    private String date;
    private double value;

    public List<StatmentModel> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<StatmentModel> statementList) {
        this.statementList = statementList;
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
