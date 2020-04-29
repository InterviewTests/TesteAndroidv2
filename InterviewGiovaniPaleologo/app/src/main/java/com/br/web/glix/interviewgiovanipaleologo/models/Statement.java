package com.br.web.glix.interviewgiovanipaleologo.models;

import java.util.Date;

public class Statement implements Comparable<Statement> {
    private String title;
    private String desc;
    private Date date;
    private double value;

    public Statement() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = new Date(date);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Statement statement) {
        if (getDate() == null || statement.getDate() == null) {
            return 0;
        }
        return getDate().compareTo(statement.getDate());
    }

}

