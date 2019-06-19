package com.example.bankapp.domain;

import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.userAccount;
import com.example.bankapp.model.userAccountModel;

public class UserDomain {

    public UserContract.IRepository repository;

    private long userId;
    private String name;
    private String bankAccount;
    private String agency;
    private double balance;

    private String userName;
    private String password;

    public UserDomain(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public UserDomain(long userId, String name, String bankAccount, String agency, double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }


    public void login(final BaseCallback<userAccountModel> listener) {
        repository.login(userName, password, new BaseCallback<userAccountModel>() {
            @Override
            public void onSuccessful(userAccountModel value) {
                listener.onSuccessful(value);
            }

            @Override
            public void onUnsuccessful(String text) {
                listener.onUnsuccessful(text);
            }
        });
    }
}
