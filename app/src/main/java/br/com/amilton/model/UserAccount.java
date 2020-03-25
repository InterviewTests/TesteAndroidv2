package br.com.amilton.model;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private String userId;
    private String name;
    private String bankAccount;
    private String agency;
    private Double balance;

    public UserAccount() {
    }

    public UserAccount(String userId, String name, String bankAccount, String agency, Double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (!name.equals(that.name)) return false;
        if (!bankAccount.equals(that.bankAccount)) return false;
        return agency.equals(that.agency);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + bankAccount.hashCode();
        result = 31 * result + agency.hashCode();
        return result;
    }
}
