package br.com.giovanni.testebank.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class UserAccount implements Serializable {

    private int id;
    private String name;
    private String agency;
    private String bankAccount;
    private double balance;

    public UserAccount(int id, String name, String agency, String bankAccount, double balance) {
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

    public String getName() {
        return name;
    }

    public String getAgency() {
        return agency;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public double getBalance() {
        return balance;
    }

}

