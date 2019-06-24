package com.example.bankapp.domain;

import android.content.Context;

import com.example.bankapp.data.remote.model.user.UserAccountModel;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.helper.ConstantsStrings;
import com.example.bankapp.helper.SaveUserPreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDomain {


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
        Pattern emailCpf = Pattern.compile(ConstantsStrings.REGEX_EMAIL_CPF);
        Pattern uppChar = Pattern.compile(ConstantsStrings.REGEX_UPPER_CHARACTERS);
        Pattern num = Pattern.compile(ConstantsStrings.REGEX_NUMBER_CHARACTERS);
        Pattern especial = Pattern.compile(ConstantsStrings.REGEX_SPECIAL_CHARACTERS);

        Matcher matcherEmailCpf = emailCpf.matcher(userName);
        Matcher matcherUpp = uppChar.matcher(password);
        Matcher matcherNum = num.matcher(password);
        Matcher matcherEspecial = especial.matcher(password);


        if (userName == null || userName.isEmpty())
            throw new Exception(ConstantsStrings.ERROR_FIELD_USER_EMPTY);

        if (password == null || password.isEmpty())
            throw new Exception(ConstantsStrings.ERROR_FIELD_PASSWORD_EMPTY);

        if (!matcherEmailCpf.find())
            throw new Exception(ConstantsStrings.ERROR_EMAIL_CPF);

        if (!matcherUpp.find())
            throw new Exception(ConstantsStrings.ERROR_PASSWORD);

        if (!matcherNum.find())
            throw new Exception(ConstantsStrings.ERROR_PASSWORD);

        if (!matcherEspecial.find())
            throw new Exception(ConstantsStrings.ERROR_PASSWORD);
    }

    private void saveLogin() {
        new SaveUserPreferences(userName, password, context);
    }

}
