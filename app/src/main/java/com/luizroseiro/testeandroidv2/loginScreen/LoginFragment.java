package com.luizroseiro.testeandroidv2.loginScreen;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    protected LoginInteractorInput output;

    public LoginFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container,
                false);

        // TODO: prepare layout and listeners

        return binding.getRoot();

    }

    private void loginUser() {

        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUser();  TODO: get user and password edit text
//        boolean isValidPassword = loginRequest.setPassword();
//
//        if (!isValidPassword)
//            showPasswordFeedback();
//        else
//            output.loginUser(loginRequest);

    }

    private void showPasswordFeedback() {
        // TODO: display password requirements feedback
    }

}
