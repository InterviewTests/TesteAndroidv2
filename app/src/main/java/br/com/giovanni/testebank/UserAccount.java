package br.com.giovanni.testebank;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class UserAccount implements Serializable {

    private int id;
    private String name;
    private String agency;
    private String bankAccount;
    private double balance;

    public UserAccount(int id, String name, String agency, String bankAccount, double balance){
        this.id = id;
        this.name = name;
        this.agency = agency;
        this.bankAccount = bankAccount;
        this.balance = balance;

    }

    public UserAccount(JSONObject jsonResposta) throws JSONException {
        this(
                jsonResposta.getInt("userId"),
                jsonResposta.getString("name"),
                jsonResposta.getString("agency"),
                jsonResposta.getString("bankAccount"),
                jsonResposta.getDouble("balance")
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
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


}

