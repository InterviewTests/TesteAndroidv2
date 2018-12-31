package com.home.project.testeandroidv2.model;

import java.io.Serializable;

public class UserAccount implements Serializable {

    private int userId;
    private String name;
    private String bankAccount;
    private String agency;
    private String balance;
    private String login;

    public UserAccount() {

    }

    public UserAccount(int userId, String name, String bankAccount, String agency, String login) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.login = login;
    }

    public String getLogin() {
        if (login == null) {
            return "";
        } else {
            return login;
        }

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        if (name == null) {
            name =  "";
        }

        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        if (bankAccount == null) {
             bankAccount = "";
        }

        return bankAccount;

    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        if(agency == null){
            agency = "";
        }
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
