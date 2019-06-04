package com.bank.service.ui.statements.domain.model;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Statements {

    // SIMULACAO;

    private String title;
    private String desc;
    private String date;
    private String value;


    public Statements(String title,
                      String desc,
                      String date,
                      String value) {

        this.title = title;
        this.desc = desc;
        this.date = getDateFormat(date);
        //this.date = date;
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
         return date ;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getDateFormat(String text){

        String dataBR ;
        try {

        //    String patr = "dd/MM/yyyy";

            //String dataEmUmFormato = "2009-10-30";
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date data = formato.parse(text);
            formato.applyPattern("dd/MM/yyyy");
            dataBR= formato.format(data);


        }catch (Exception e){
            dataBR = text;
        }

        return dataBR;
    }











}
