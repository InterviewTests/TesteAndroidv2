package com.example.bankapp.domain;

import android.content.Context;

import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.helper.SaveUserPreferences;
import com.example.bankapp.model.user.UserAccountModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDomain {
    private static final String ERROR_PASSWORD = "Senha não atende os requisitos";

    public UserContract.IRepository repository;
    private Context context;
    private String userName;
    private String password;

    public UserDomain(String userName, String password, Context context) {
        this.userName = userName.trim();
        this.password = password.trim();
        this.context = context;
    }


    public void login(final BaseCallback<UserAccountModel> listener) throws Exception {
        validateFields();

        repository.login(userName, password, new BaseCallback<UserAccountModel>() {
            @Override
            public void onSuccessful(UserAccountModel value) {
                listener.onSuccessful(value);
                saveLogin();
            }

            @Override
            public void onUnsuccessful(String text) {
                listener.onUnsuccessful(text);
            }
        });
    }

    private void validateFields() throws Exception {
        Pattern uppChar = Pattern.compile("[A-Z]");
        Pattern lowChar = Pattern.compile("[a-z]");
        Pattern num = Pattern.compile("[0-9]");
        Pattern especial = Pattern.compile("[$&+,:;=?@#|'<>.^*()%!-]");

        Matcher matcherUpp = uppChar.matcher(password);
        Matcher matcherLow = lowChar.matcher(password);
        Matcher matcherNum = num.matcher(password);
        Matcher matcherEpecial = especial.matcher(password);

        if (userName == null || userName.isEmpty())
            throw new Exception("Preencha o campo user");

        if (password == null || password.isEmpty())
            throw new Exception("Preencha o campo senha");

        if (!matcherUpp.find())
            throw new Exception(ERROR_PASSWORD);

        if (!matcherLow.find())
            throw new Exception(ERROR_PASSWORD);

        if (!matcherNum.find())
            throw new Exception(ERROR_PASSWORD);

        if (!matcherEpecial.find())
            throw new Exception(ERROR_PASSWORD);
    }

    private void saveLogin() {
        new SaveUserPreferences(userName, password, context);
    }
}
