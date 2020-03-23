package com.bankapp.loginScreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.bankapp.R;


interface LoginActivityInput {
    public void signIn(LoginViewModel viewModel);
    public void errorSignIn(LoginViewModel viewModel);
    public void bindLoginFields(LoginViewModel loginViewModel);
    public void errorUserOrPasswordInvalid(LoginViewModel loginViewModel);
   }

public class LoginActivity extends AppCompatActivity implements LoginActivityInput, View.OnClickListener {

    public static String TAG = LoginActivity.class.getSimpleName();

    public UserAccount userAccount;

    LoginInteractorInput output;
    LoginRouterInput router;

    private AppCompatEditText textInputUser;
    private AppCompatEditText textInputPass;
    private AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginConfigurator.INSTANCE.configure(this);

        textInputUser = findViewById(R.id.textInputUser);
        textInputPass = findViewById(R.id.textInputPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        getSavedUser();
    }

    /**
     * Try to do login
     * @param v
     */
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnLogin) {
            if(checkFields()){
                setLoading(true);
                showProgressDialog();
                LoginRequest aLoginRequest = new LoginRequest();
                LoginModel loginModel = new LoginModel(textInputUser.getText().toString(), textInputPass.getText().toString());
                aLoginRequest.loginModel = loginModel;
                output.doLogin(this, aLoginRequest);
            }
        }
    }

    /**
     * Called by presenter when fields have an unexpected format
     * @param loginViewModel
     */
    @Override
    public void errorUserOrPasswordInvalid(LoginViewModel loginViewModel){
        setLoading(false);
        hideProgressDialog();
        if(loginViewModel.wrongUser){
            textInputUser.setError(getString(R.string.error_wrong_user));
        }
        if(loginViewModel.wrongPassword){
            textInputPass.setError(getString(R.string.error_wrong_pass));
        }
    }

    /**
     * Called by presenter when login validation worked
     * @param viewModel
     */
    @Override
    public void signIn(LoginViewModel viewModel) {
        setLoading(false);
        hideProgressDialog();
        userAccount = viewModel.userAccount;
        router.passDataToNextScene();
    }

    /**
     * Called by presenter when login validation didn't work
     * @param viewModel
     */
    @Override
    public void errorSignIn(LoginViewModel viewModel) {
        setLoading(false);
        hideProgressDialog();
        Toast.makeText(this, viewModel.error.toString(), Toast.LENGTH_LONG).show();
    }

    /**
     * Called by presenter when has a last logged user
     * Then fill fields {textInputUser} and {textInputPass}
     * @param loginViewModel
     */
    @Override
    public void bindLoginFields(LoginViewModel loginViewModel){
        textInputUser.setText(loginViewModel.loginModel.user);
        textInputPass.setText(loginViewModel.loginModel.password);
        setFieldsEnabled(true);
    }

    /**
     * Search for last logged user
     */
    public void getSavedUser(){
        setFieldsEnabled(false);
        output.getSavedUser(this);
    }

    /**
     * Disable all fields
     * @param disable
     */
    private void setFieldsEnabled(boolean disable){
        textInputUser.setEnabled(disable);
        textInputPass.setEnabled(disable);
        btnLogin.setEnabled(disable);
    }

    /**
     * Set loading decoration
     * @param loading
     */
    public void setLoading(boolean loading){
        setFieldsEnabled(!loading);
        if(loading) {
            btnLogin.setText(getString(R.string.loading));
        } else {
            btnLogin.setText(getString(R.string.login));
        }
    }

    /**
     * Check required fields
     * @return
     */
    private boolean checkFields(){
        if(TextUtils.isEmpty(textInputUser.getText())){
            textInputUser.setError(getString(R.string.error_required_field));
            return false;
        }
        if(TextUtils.isEmpty(textInputPass.getText())){
            textInputPass.setError(getString(R.string.error_required_field));
            return false;
        }
        return true;
    }

    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
