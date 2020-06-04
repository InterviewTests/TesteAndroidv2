package br.com.altran.santander.davidmelo.model;

public class UserAccount {
    private int userId;
    private String name;
    private String bankAccount;
    private Double balance;
    private String agency;

    public UserAccount(int userId, String name, String bankAccount, Double balance, String agency) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.balance = balance;
        this.agency = agency;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        agency = agency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        balance = balance;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        userId = userId;
    }

}
