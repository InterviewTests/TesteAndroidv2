package com.resourceit.app.views;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.resourceit.app.R;
import com.resourceit.app.models.LoginModel;
import com.resourceit.app.models.StatmentModel;
import com.resourceit.app.tools.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {


    @BindView(R.id.user_layout) TextInputLayout user_layout;
    @BindView(R.id.password_layout) TextInputLayout password_layout;
    @BindView(R.id.user) TextView user;
    @BindView(R.id.password) TextView password;
    private MainActivity activity;
    private Boolean doLogin = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();
        
        return view;
    }

    @OnClick(R.id.login)
    void Login() {
        if(!doLogin) {
            doLogin = true;
            if (!validateUser(user.getText().toString())) {
                user_layout.setError("Não é um usuário válido!");
                doLogin = false;
            }else user_layout.setErrorEnabled(false);
            if (!validatePassword(password.getText().toString())) {
                password_layout.setError("Não é uma senha válida!");
                doLogin = false;
            }else password_layout.setErrorEnabled(false);
            if(doLogin){
                hideKeyboard();
                activity.Loading(true);
                Call<LoginModel> statments = activity.API.DoLogin(user.getText().toString(), password.getText().toString());
                final Gson gson = new Gson();

                statments.enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        LoginModel login = response.body();
                        Log.w("login:sucess:: ", gson.toJson(login.getUserAccount()));
                        activity.loginDao.insertAll(login.getUserAccount());
                        Log.w("login:sucess:db: ", gson.toJson(activity.loginDao.findById(1)));
                        activity.updateFragment(new StatementsFragment(), "STATMENTS");
                        activity.Loading(false);
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        Log.w("login:error:: ", t.toString());
                        activity.Loading(false);
                        doLogin = false;
                    }
                });
            }
        }
    }

    private void hideKeyboard() {
        View view = activity.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean validateUser(String user) {
        return Validator.isValidEmail(user) || Validator.isValidCPF(user);
    }

    public boolean validatePassword(String password) {
        return Validator.isValidPassword(password);
    }


}
