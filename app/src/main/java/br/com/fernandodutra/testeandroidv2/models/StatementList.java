package br.com.fernandodutra.testeandroidv2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 21/06/2019
 * Time: 11:32
 * TesteAndroidv2
 */
public class StatementList implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("value")
    @Expose
    private Double value;
    private String formatedValue;

    public StatementList() {

    }

    public StatementList(String title, String desc, String date, Double value) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
    }

    public StatementList(String title, String desc, String date, Double value, String formatedValue) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
        this.formatedValue = formatedValue;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getFormatedValue() {
        return formatedValue;
    }

    public void setFormatedValue(String formatedValue) {
        this.formatedValue = formatedValue;
    }
}
