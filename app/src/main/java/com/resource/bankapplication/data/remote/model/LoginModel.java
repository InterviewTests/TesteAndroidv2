package com.resource.bankapplication.data.remote.model;

import com.resource.bankapplication.domain.UserAccount;

public class LoginModel {
    private Long userId;
    private String name;
    private String bankAccount;
    private String agency;
    private Double balance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UserAccount toDomain() {
        return new UserAccount(userId, name, bankAccount, agency, balance);
    }
}
