package com.bilulo.androidtest04.ui.login.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bilulo.androidtest04.R;
import com.bilulo.androidtest04.common.BaseActivity;
import com.bilulo.androidtest04.components.UserEditText;
import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.ui.login.configurator.LoginConfigurator;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.router.LoginRouter;
import com.bilulo.androidtest04.utils.SharedPreferencesUtil;
import com.bilulo.androidtest04.utils.ValidationUtil;

public class LoginActivity extends BaseActivity implements LoginContract.ActivityContract, View.OnClickListener {

    public static final String KEY_USER = "key-user";
    public LoginContract.InteractorContract interactor;
    public LoginRouter router;

    public UserEditText edtUser;
    public EditText edtPassword;
    public Button btnLogin;

    private boolean passwordNotEmpty;
    private boolean hasUppercase;
    private boolean hasSpecial;
    private boolean hasAlphanumeric;

    private boolean userNotEmpty;
    private boolean validCpf;
    private boolean validEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginConfigurator.configure(this);
        hideActionBar();
        findViews();
        resetValidationVariables();
        loadUsername();
    }

    private void resetValidationVariables() {
        //password
        passwordNotEmpty = false;
        hasUppercase = false;
        hasSpecial = false;
        hasAlphanumeric = false;
        //user
        userNotEmpty = false;
        validCpf = false;
        validEmail = false;
    }

    private void findViews() {
        edtUser = findViewById(R.id.edt_user);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    private void loadUsername() {
        String userName = SharedPreferencesUtil.getString(this, KEY_USER, null);
        if (ValidationUtil.isValidString(userName)) {
            edtUser.setText(userName);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnLogin)) {
            String userText = edtUser.getText() != null ? edtUser.getText().toString() : "";
            String passwordText = edtPassword.getText() != null ? edtPassword.getText().toString() : "";
            if (isValidLogin(userText, passwordText)) {
                showProgressDialog();
                interactor.performLogin(userText, passwordText);
            }
        }
    }

    private boolean isValidLogin(String userText, String passwordText) {
        if (isValidUser(userText) && isValidPassword(passwordText)) {
            return true;
        } else {
            //TODO create
            //createValidationErrorAlertDialog();
            return false;
        }
    }

    public boolean isValidPassword(String passwordText) {
        resetValidationVariables();
        if (passwordText != null) {
            if (ValidationUtil.isValidString(passwordText)) {
                if (ValidationUtil.hasUppercase(passwordText)) {
                    hasUppercase = true;
                }
                if (ValidationUtil.hasSpecialCharacter(passwordText)) {
                    hasSpecial = true;
                }
                if (ValidationUtil.hasAlphanumericCharacter(passwordText)) {
                    hasAlphanumeric = true;
                }
                if (!hasUppercase || !hasSpecial || !hasAlphanumeric) {
                    //showAlertDialog(hasUppercase, hasSpecial, hasAlphanumeric);
                    return false;
                } else
                    return true;
            } else {
                //showAlertDialog(getString(R.string.validation_error_empty_password));
                passwordNotEmpty = false;
                return false;
            }
        }
        passwordNotEmpty = false;
        //showAlertDialog(getString(R.string.validation_error_empty_password));
        return false;
    }

    public boolean isValidUser(String userText) {
        resetValidationVariables();
        if (userText != null) {
            if (ValidationUtil.isValidString(userText)) {
                String aux = userText.replace(".", "").replace("-", "");
                if (ValidationUtil.isFullCpf(userText) || aux.matches("[0-9]+")) {
                    if (ValidationUtil.isValidCpf(userText)) {
                        return true;
                    } else {
                        validCpf = false;
                        //showAlertDialog(getString(R.string.validation_error_user_cpf));
                        return false;
                    }
                } else {
                    if (ValidationUtil.isValidEmail(userText)) {
                        return true;
                    } else {
                        validEmail = false;
                        //showAlertDialog(getString(R.string.validation_error_user_email));
                        return false;
                    }
                }
            } else {
                userNotEmpty = false;
                //showAlertDialog(getString(R.string.validation_error_empty_user));
                return false;
            }
        } else {
            userNotEmpty = false;
            //showAlertDialog(getString(R.string.validation_error_empty_user));
            return false;
        }
    }

    private void showAlertDialog(boolean hasUppercase, boolean hasSpecial, boolean hasAlphanumeric) {
        String uppercase = getString(R.string.validation_error_password_uppercase);
        String special = getString(R.string.validation_error_password_special);
        String alphanumeric = getString(R.string.validation_error_password_alphanumeric);
        String message = getString(R.string.validation_error_password, uppercase, special, alphanumeric);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(message);
        int uppercaseStartIndex = message.indexOf(uppercase);
        int uppercaseEndIndex = uppercaseStartIndex + uppercase.length();
        int specialStartIndex = message.indexOf(special);
        int specialEndIndex = specialStartIndex + special.length();
        int alphanumericStartIndex = message.indexOf(alphanumeric);
        int alphanumericEndIndex = alphanumericStartIndex + alphanumeric.length();
        if (!hasUppercase) {
            spannableStringBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), uppercaseStartIndex, uppercaseEndIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            //spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), uppercaseStartIndex, uppercaseEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (!hasSpecial) {
            spannableStringBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), specialStartIndex, specialEndIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            //spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), specialStartIndex, specialEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (!hasAlphanumeric) {
            spannableStringBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), alphanumericStartIndex, alphanumericEndIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            //spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), alphanumericStartIndex, alphanumericEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        showAlertDialog(spannableStringBuilder);
    }

    @Override
    public void loginSuccessful(UserAccountModel userAccountModel) {
        String userName = edtUser.getText().toString();
        hideProgressDialog();
        router.loginSuccessful(userAccountModel, userName);
    }

    @Override
    public void displayError() {
        hideProgressDialog();
        showAlertDialog(getString(R.string.login_server_error));
    }
}
