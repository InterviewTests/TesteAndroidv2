package com.gft.testegft.statements.data;

import com.gft.testegft.login.data.LoginResponse;

import java.text.NumberFormat;
import java.util.Locale;

public class UserData {
    private String name;
    private String account;
    private String balance;

    public UserData(String name, String account, String balance) {
        this.name = name;
        this.account = account;
        this.balance = balance;
    }

    public UserData(LoginResponse loginResponse) {
        this.name = loginResponse.getUserAccount().getName();
        this.account = accountFormat(loginResponse.getUserAccount().getAgency(), loginResponse.getUserAccount().getBankAccount());
        this.balance = balanceFormat(loginResponse.getUserAccount().getBalance());
    }

    private String accountFormat(String agency, String bankAccount) {
        String account = "";

        try {
            account += bankAccount + " / ";
            account += agency.substring(0,1);
            account += '.';
            account += agency.substring(2,7);
            account += '-';
            account += agency.substring(8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }

    private String balanceFormat(double balance) {
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

        return formatter.format(balance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
