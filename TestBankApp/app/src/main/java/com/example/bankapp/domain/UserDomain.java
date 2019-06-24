package com.example.bankapp.domain;

import android.content.Context;

import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.helper.SaveUserPreferences;
import com.example.bankapp.model.user.UserAccountModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDomain {
    private static final String ERROR_PASSWORD = "Senha não atende os requisitos";
    private static final String ERROR_FIELD_USER_EMPTY = "Preencha o campo user";
    private static final String ERROR_FIELD_PASSWORD_EMPTY = "Preencha o campo password";
    private static final String ERROR_EMAIL_CPF = "Preencha o campo user com um email ou cpf válidos";

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

    public void validateFields() throws Exception {
        Pattern emailCpf = Pattern.compile(".+@.+\\..+|[0-9]{11}");
        Pattern uppChar = Pattern.compile("[A-Z]");
        Pattern num = Pattern.compile("[0-9]");
        Pattern especial = Pattern.compile("[$&+,:;=?@#|'<>.^*()%!-]");

        Matcher matcherEmailCpf = emailCpf.matcher(userName);
        Matcher matcherUpp = uppChar.matcher(password);
        Matcher matcherNum = num.matcher(password);
        Matcher matcherEspecial = especial.matcher(password);


        if (userName == null || userName.isEmpty())
            throw new Exception(ERROR_FIELD_USER_EMPTY);

        if (password == null || password.isEmpty())
            throw new Exception(ERROR_FIELD_PASSWORD_EMPTY);

        if (!matcherEmailCpf.find())
            throw new Exception(ERROR_EMAIL_CPF);

        if (!matcherUpp.find())
            throw new Exception(ERROR_PASSWORD);

        if (!matcherNum.find())
            throw new Exception(ERROR_PASSWORD);

        if (!matcherEspecial.find())
            throw new Exception(ERROR_PASSWORD);
    }

    private void saveLogin() {
        new SaveUserPreferences(userName, password, context);
    }

}
