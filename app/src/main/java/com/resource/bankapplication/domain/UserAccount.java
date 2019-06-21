package com.resource.bankapplication.domain;

import android.content.Context;

import com.resource.bankapplication.config.BaseCallback;

import java.io.Serializable;

public class UserAccount implements Serializable {

    public UserAccountContract.IRepository repository;
    private Long id;
    private String name;
    private String bankAccount;
    private String agency;
    private Double balance;

    private String username;
    private String password;

    public UserAccount() {
    }

    public UserAccount(Long id, String name, String bankAccount, String agency, Double balance) {
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

    public Double getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void login(BaseCallback<UserAccount> onResult){
        if(repository == null){
            onResult.onUnsuccessful("Repository não pode ser nulo!");
            return;
        }
        if(username.isEmpty()){
            onResult.onUnsuccessful("Usuário não pode ser nulo!");
            return;
        }

        if(password.isEmpty()){
            onResult.onUnsuccessful("Senha não pode ser nulo!");
            return;
        }
        if(!validUsername()) {
            onResult.onUnsuccessful("Usuário invalido!");
            return;
        }
        if(!validPassword()){
            onResult.onUnsuccessful("Senha não é valida!");
            return;
        }

        repository.login(username, password, new BaseCallback<UserAccount>() {
            @Override
            public void onSuccessful(UserAccount value) {
                onResult.onSuccessful(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                onResult.onUnsuccessful(error);
            }
        });
    }

    private boolean validPassword() {
//        if(password.matches("([a-z]+[A-Z]+[0-9]+[?=.*}{,@!'#$%¨&:;|<>^~+_]+)"))
//            return true;
        return true;
    }

    private boolean validUsername() {
//        if(username.matches(".+@.+\\..+")) return true;
//        if(username.matches("[0-9]{11}")) return true;
        return true;
    }

    public void loadPreference(Context context, BaseCallback<UserAccount> onResult) {
        repository.loadPreference(context, new BaseCallback<UserAccount>() {
            @Override
            public void onSuccessful(UserAccount value) {
                onResult.onSuccessful(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                onResult.onUnsuccessful(error);
            }
        });
    }
}
