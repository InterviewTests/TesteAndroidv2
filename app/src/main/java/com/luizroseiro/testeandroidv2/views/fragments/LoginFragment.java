package com.luizroseiro.testeandroidv2.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.utils.Utils;
import com.luizroseiro.testeandroidv2.views.activities.MainActivity;

public class LoginFragment extends Fragment {

    private ConstraintLayout clLoginContent;
    private ConstraintLayout clSplash;
    private EditText etUser;
    private EditText etPassword;
    private Button btnLogin;

    private boolean userVerification = false;
    private boolean passwordVerification = false;

    public LoginFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        clLoginContent = view.findViewById(R.id.cl_login_content);
        clSplash = view.findViewById(R.id.cl_splash);

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
                loginAction();
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

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                    loginAction();
                return false;
            }
        });

    }

    private void loginAction() {
        loadingFeedback();
        MainActivity.dataService.loginUser(LoginFragment.this,
                etUser.getText().toString(), etPassword.getText().toString());
    }

    private void loadingFeedback() {
        clLoginContent.setClickable(false);
        clSplash.setVisibility(View.VISIBLE);
    }

    public void dismissLoadingFeedback() {
        clLoginContent.setClickable(true);
        clSplash.setVisibility(View.GONE);
    }

}
