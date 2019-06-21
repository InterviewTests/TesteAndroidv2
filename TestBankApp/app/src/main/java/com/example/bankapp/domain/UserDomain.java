package com.example.bankapp.domain;

import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.user.UserAccountModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public void login(final BaseCallback<UserAccountModel> listener) throws Exception {
        Pattern uppChar = Pattern.compile("[A-Z]");
        Pattern lowChar = Pattern.compile("[a-z]");
        Pattern num = Pattern.compile("[0-9]");
        Pattern especial = Pattern.compile("[$&+,:;=?@#|'<>.^*()%!-]");

        Matcher matcherUpp = uppChar.matcher(password);
        Matcher matcherLow = lowChar.matcher(password);
        Matcher matcherNum = num.matcher(password);
        Matcher matcherEpecial = especial.matcher(password);

        if(userName == null || userName.isEmpty())
            throw new Exception("Preencha o campo user");

        if(password==null || password.isEmpty())
            throw new Exception("Preencha o campo senha");

        if(!matcherUpp.find())
            throw new Exception("Senha deve conter ao menos uma letra maiuscula");

        if(!matcherLow.find())
            throw new Exception("Senha deve conter ao menos uma letra minuscula");

        if(!matcherNum.find())
            throw new Exception("Senha deve conter ao menos numero");

        if(!matcherEpecial.find())
            throw new Exception("Senha deve conter ao menos um caractere especial");

        repository.login(userName, password, new BaseCallback<UserAccountModel>() {
            @Override
            public void onSuccessful(UserAccountModel value) {
                listener.onSuccessful(value);
            }

            @Override
            public void onUnsuccessful(String text) {
                listener.onUnsuccessful(text);
            }
        });
    }
}
