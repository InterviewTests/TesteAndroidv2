package com.resource.bankapplication.data.remote.dto;


public class LoginDto {
    private long userId;
    private String name;
    private String bankAccount;
    private String agency;
    private double balance;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
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

    public com.resource.bankapplication.domain.UserAccount toDomain() {
        return new com.resource.bankapplication.domain.UserAccount(userId, name, bankAccount, agency, balance);
    }
}
