package br.com.kakobotasso.testeandroidv2.currency;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CurrencyItem {
    private String title;
    @SerializedName("desc")
    private String description;
    private Date date;
    private double value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
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
