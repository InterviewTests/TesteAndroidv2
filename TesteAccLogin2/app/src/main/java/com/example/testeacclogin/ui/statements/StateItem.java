package com.example.testeacclogin.ui.statements;

public class StateItem {
    private String mTitle;
    private String mDesc;
    private String mData;
    private String mValue;


    public StateItem(String title, String desc, String data, String value){
        mTitle = title;
        mDesc = desc;
        mData = data;
        mValue = value;

    }

    public String getmTitle(){
        return mTitle;
    }

    public String getmDesc(){
        return mDesc;
    }

    public String getmData(){
        return mData;
    }

    public String getmValue(){
        return mValue;
    }

}
