package com.luizroseiro.testeandroidv2.loginScreen;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.luizroseiro.testeandroidv2.MainActivity;
import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.databinding.FragmentLoginBinding;
import com.luizroseiro.testeandroidv2.utils.Utils;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    protected LoginInteractorInput output;

    private boolean passwordValidation;
    private boolean userValidation;

    public LoginFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container,
                false);

        output = new LoginInteractor();

        setListeners();

        return binding.getRoot();

    }

    private void setListeners() {

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        binding.etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_NEXT) {
                    InputMethodManager imm = (InputMethodManager) MainActivity.getContext()
                            .getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    loginUser();
                }

                return false;
            }
        });

        binding.etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    userValidation = true;
                    if (passwordValidation)
                        Utils.enableButton(binding.btnLogin);
                }
                else if (userValidation) {
                    userValidation = false;
                    Utils.disableButton(binding.btnLogin);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    passwordValidation = true;
                    if (userValidation)
                        Utils.enableButton(binding.btnLogin);
                }
                else if (passwordValidation) {
                    passwordValidation = false;
                    Utils.disableButton(binding.btnLogin);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    private void loginUser() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUser(binding.etUser.getText().toString());
        boolean isValidPassword = loginRequest.setPassword(binding.etPassword.getText().toString());

        if (!isValidPassword)
            showPasswordFeedback();
        else
            output.loginUser(loginRequest);

    }

    private void showPasswordFeedback() {
        binding.clPasswordFeedback.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.clPasswordFeedback.setVisibility(View.GONE);
            }
        }, 10000);
    }

}