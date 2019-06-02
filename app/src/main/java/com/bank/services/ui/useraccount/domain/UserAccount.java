package com.bank.services.ui.useraccount.domain;

public class UserAccount {

    private float userId;
    private String name;
    private String bankAccount;
    private String agency;
    private float balance;


    // Getter Methods

    public float getUserId() {
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

    public float getBalance() {
        return balance;
    }

    // Setter Methods

    public void setUserId( float userId ) {
        this.userId = userId;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setBankAccount( String bankAccount ) {
        this.bankAccount = bankAccount;
    }

    public void setAgency( String agency ) {
        this.agency = agency;
    }

    public void setBalance( float balance ) {
        this.balance = balance;
    }



}
