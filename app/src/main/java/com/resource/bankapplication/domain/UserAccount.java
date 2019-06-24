package com.resource.bankapplication.domain;

import android.content.Context;

import com.resource.bankapplication.ConstantsApp;
import com.resource.bankapplication.config.BaseCallback;

import java.io.Serializable;

public class UserAccount implements Serializable {

    private UserAccountContract.IRepository repository;
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

    public void setRepository(UserAccountContract.IRepository repository) {
        this.repository = repository;
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
            onResult.onUnsuccessful(ConstantsApp.REPOSITORY_NULL);
            return;
        }
        if(username.isEmpty()){
            onResult.onUnsuccessful(ConstantsApp.USER_NULL);
            return;
        }

        if(password.isEmpty()){
            onResult.onUnsuccessful(ConstantsApp.PASSWORD_NULL);
            return;
        }
        if(!validUsername()) {
            onResult.onUnsuccessful(ConstantsApp.USER_INVALID);
            return;
        }
        if(!validPassword()){
            onResult.onUnsuccessful(ConstantsApp.PASSWORD_INVALID);
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
        if(password.matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!&*()_=+{}?;:><,.|']).{4,})"))
            return true;
        return false;
    }

    private boolean validUsername() {
        if(username.matches(".+@.+\\..+")) return true;
        if(username.matches("[0-9]{11}")) return true;
        return false;
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
