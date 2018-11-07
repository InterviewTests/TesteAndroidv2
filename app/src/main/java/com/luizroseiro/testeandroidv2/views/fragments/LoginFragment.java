package com.luizroseiro.testeandroidv2.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.utils.Utils;
import com.luizroseiro.testeandroidv2.views.activities.MainActivity;

public class LoginFragment extends Fragment {

    private EditText etUser;
    private EditText etPassword;

    private boolean userVerification = false;
    private boolean passwordVerification = false;

    private Button btnLogin;

    public LoginFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etUser = view.findViewById(R.id.et_user);
        etPassword = view.findViewById(R.id.et_password);

        btnLogin = view.findViewById(R.id.btn_login);

        setListeners();

        return view;

    }

    private void setListeners() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dataService.loginUser(etUser.getText().toString(),
                        etPassword.getText().toString());
            }
        });

        etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etUser.getText().toString().length() > 0) {
                    userVerification = true;
                    if (passwordVerification) Utils.enableButton(btnLogin);
                }
                else if (userVerification) {
                    userVerification = false;
                    Utils.disableButton(btnLogin);
                }
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Utils.isValidPassword(etPassword.getText().toString())) {
                    passwordVerification = true;
                    if (userVerification) Utils.enableButton(btnLogin);
                }
                else if (passwordVerification) {
                    passwordVerification = false;
                    Utils.disableButton(btnLogin);
                }
            }
        });

    }

    // TODO: handle enter button

}
