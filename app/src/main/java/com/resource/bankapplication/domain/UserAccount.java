package com.resource.bankapplication.domain;

public class UserAccount {

    private Long id;
    private String name;
    private String bankAccount;
    private String agency;
    private String balance;

    private String username;
    private String password;

    public UserAccount(Long id, String name, String bankAccount, String agency, String balance) {
        this.id = id;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
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

    public String getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
