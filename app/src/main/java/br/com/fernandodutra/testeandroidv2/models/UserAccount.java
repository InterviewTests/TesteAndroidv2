package br.com.fernandodutra.testeandroidv2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 21/06/2019
 * Time: 11:31
 * TesteAndroidv2
 */
public class UserAccount implements Serializable {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bankAccount")
    @Expose
    private String bankAccount;
    @SerializedName("agency")
    @Expose
    private String agency;
    @SerializedName("balance")
    @Expose
    private Double balance;

    public UserAccount() {
    }

    public UserAccount(Integer userId, String name, String bankAccount, String agency, Double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
}
