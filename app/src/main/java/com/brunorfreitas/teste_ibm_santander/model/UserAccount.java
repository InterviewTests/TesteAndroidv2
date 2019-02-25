package com.brunorfreitas.teste_ibm_santander.model;

public class UserAccount {

    private long userid;
    private String name;
    private String bankaccount;
    private String agency;
    private double balance;

    public UserAccount() {
    }

    public UserAccount(long userid, String name, String bankaccount, String agency, double balance) {
        this.userid = userid;
        this.name = name;
        this.bankaccount = bankaccount;
        this.agency = agency;
        this.balance = balance;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
