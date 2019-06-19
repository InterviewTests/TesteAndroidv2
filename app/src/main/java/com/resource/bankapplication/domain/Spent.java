package com.resource.bankapplication.domain;

public class Spent {

    private String typeTransaction;
    private String description;
    private String date;
    private String value;

    public Spent(String typeTransaction, String description, String date, String value) {
        this.typeTransaction = typeTransaction;
        this.description = description;
        this.date = date;
        this.value = value;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }
}
