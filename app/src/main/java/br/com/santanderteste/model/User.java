package br.com.santanderteste.model;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class User {

    private String userId;
    private String name;
    private String bankAccount;
    private String agency;
    private double balance;

    public User(String userId, String name, String bankAccount, String agency, double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.balance = balance;
        this.agency = agency;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }
}
