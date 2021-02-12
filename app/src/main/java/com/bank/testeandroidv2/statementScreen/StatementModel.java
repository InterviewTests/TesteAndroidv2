package com.bank.testeandroidv2.statementScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StatementModel {
//    Long userId;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("desc")
    @Expose
    String desc;
    @SerializedName("date")
    @Expose
    String date;
    @SerializedName("value")
    @Expose
    String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    ArrayList<StatementModel> statementList;
//    StatementList statementList;
}

class StatementViewModel {
    String title;
    String desc;
    String date;
    String value;
    boolean positive;
    ArrayList<StatementViewModel> list;
//    StatementList statementList;
}

class StatementRequest {
    Long userId;

}

class StatementResponse {
//    String title;
//    String desc;
//    String date;
//    String value;
    ArrayList<StatementModel> list;
}

//class StatementList {
//    ArrayList<StatementModel> list;
//}
