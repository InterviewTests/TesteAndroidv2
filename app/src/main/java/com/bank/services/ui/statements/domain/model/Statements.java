package com.bank.services.ui.statements.domain.model;

import java.util.Date;
import java.util.List;

public class Statements {

    public String title;
    public String desc;
    public Date date;
    public Double value;


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

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }



    //java.text.DateFormat dateFormat = android.text.format.DateFormat
    // .getDateFormat(getApplicationContext());

}
