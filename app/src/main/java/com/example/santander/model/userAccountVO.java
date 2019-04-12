package com.example.santander.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class userAccountVO implements Serializable {

    @Json(name = "userId")
    private Integer userId;

    @Json(name = "name")
    private String name;

    @Json(name = "bankAccount")
    private String bandAccount;

    @Json(name = "agency")
    private String agency;

    @Json(name = "balance")
    private Double balance;

    public userAccountVO(Integer userId, String name, String bankAccount, String agency, Double balance) {
        this.userId = userId;
        this.name = name;
        this.bandAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getBankAccount() {
        return bandAccount;
    }

    public String getAgency() {
        return agency;
    }

    public Double getBalance() {
        return balance;
    }
}
