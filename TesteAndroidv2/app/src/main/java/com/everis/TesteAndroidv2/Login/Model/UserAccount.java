package com.everis.TesteAndroidv2.Login.Model;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private Integer userId;
    private String name;
    private String bankAccount;
    private String agency;
    private Float balance;

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public Float getBalance() {
        return balance;
    }
}
