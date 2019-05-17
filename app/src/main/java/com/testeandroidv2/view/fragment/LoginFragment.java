package com.testeandroidv2.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.testeandroidv2.R;
import com.testeandroidv2.contract.view.LoginView;
import com.testeandroidv2.controller.LoginController;
import com.testeandroidv2.model.User;
import com.testeandroidv2.utility.ProgressDialog;
import com.testeandroidv2.view.activity.ErrorActivity;
import com.testeandroidv2.view.activity.LoginActivity;

import java.util.Objects;


public class LoginFragment extends Fragment implements LoginView {

    public static final String TAG = LoginFragment.class.getSimpleName();

    private ProgressDialog progressDialog;
    private TextInputEditText txtUserValue;
    private TextInputEditText txtPasswordValue;
    private TextInputLayout userLayout;
    private TextInputLayout passwordLayout;
    private Button buttonLogin;
    private LoginController loginController;
    private User user;
    private String userName;
    private String password;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.login_fragment, container, false);

        txtUserValue = vw.findViewById(R.id.txtUserValue);
        txtPasswordValue = vw.findViewById(R.id.txtPasswordValue);
        userLayout = vw.findViewById(R.id.userLayout);
        passwordLayout = vw.findViewById(R.id.passwordLayout);
        buttonLogin = vw.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(buttonLoginClick);

        loginController = new LoginController(this);

        return vw;
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog();
        progressDialog.show(getActivity().getSupportFragmentManager(),null);
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessageInvalidCPF() {
        txtUserValue.requestFocus();
        userLayout.setError(getResources().getString(R.string.cpf_invalid));
    }

    @Override
    public void showMessageInvalidEmail() {
        txtUserValue.requestFocus();
        userLayout.setError(getResources().getString(R.string.email_invalid));
    }

    @Override
    public void showLoggedInInterface() {

        userLayout.setError(null);
        passwordLayout.setError(null);

        LoggedFragment fragment = new LoggedFragment();
        ((LoginActivity) getActivity()).changeFragment(fragment, LoggedFragment.TAG);
    }

    @Override
    public void showErrorActivity() {
        Intent intent = new Intent(getActivity(), ErrorActivity.class);
        getActivity().startActivity(intent);
    }

    private View.OnClickListener buttonLoginClick = new View.OnClickListener() {
        public void onClick(View v) {
            user = new User();
            userName = Objects.requireNonNull(txtUserValue.getText()).toString();
            password = txtPasswordValue.getText().toString();
            user.setUser(userName);
            user.setPassword(password);

            loginController.validateCredentials(user);
        }
    };
}
