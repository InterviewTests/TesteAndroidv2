package com.bilulo.androidtest04.ui.login.view;

import android.os.Bundle;
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
    private boolean passwordHasUppercase;
    private boolean passwordHasSpecial;
    private boolean passwordHasAlphanumeric;

    private boolean userNotEmpty;
    private Boolean userIsCpf;
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
        resetUserValidationVariables();
        resetPasswordValidationVariables();
    }

    private void resetPasswordValidationVariables() {
        //password
        passwordNotEmpty = false;
        passwordHasUppercase = false;
        passwordHasSpecial = false;
        passwordHasAlphanumeric = false;
    }

    private void resetUserValidationVariables() {
        //user
        userNotEmpty = false;
        userIsCpf = null;
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
            createValidationErrorAlertDialog();
            return false;
        }
    }

    private void createValidationErrorAlertDialog() {
        if (!userNotEmpty) {
            showAlertDialog(getString(R.string.validation_error_empty_user));
        } else if (Boolean.TRUE.equals(userIsCpf) && !validCpf) {
            showAlertDialog(getString(R.string.validation_error_user_cpf));
        } else if (Boolean.FALSE.equals(userIsCpf) && !validEmail) {
            showAlertDialog(getString(R.string.validation_error_user_email));
        } else if (!passwordNotEmpty) {
            showAlertDialog(getString(R.string.validation_error_empty_password));
        } else if (!passwordHasAlphanumeric || !passwordHasSpecial || !passwordHasUppercase) {
            showAlertDialog(passwordHasUppercase, passwordHasSpecial, passwordHasAlphanumeric);
        }
    }

    public boolean isValidPassword(String passwordText) {
        resetPasswordValidationVariables();
        if (passwordText != null) {
            if (ValidationUtil.isValidString(passwordText)) {
                passwordNotEmpty = true;
                if (ValidationUtil.hasUppercase(passwordText)) {
                    passwordHasUppercase = true;
                }
                if (ValidationUtil.hasSpecialCharacter(passwordText)) {
                    passwordHasSpecial = true;
                }
                if (ValidationUtil.hasAlphanumericCharacter(passwordText)) {
                    passwordHasAlphanumeric = true;
                }
                return passwordHasUppercase && passwordHasSpecial && passwordHasAlphanumeric;
            } else {
                passwordNotEmpty = false;
                return false;
            }
        }
        passwordNotEmpty = false;
        return false;
    }

    public boolean isValidUser(String userText) {
        resetUserValidationVariables();
        if (userText != null) {
            if (ValidationUtil.isValidString(userText)) {
                userNotEmpty = true;
                String aux = userText.replace(".", "").replace("-", "");
                if (ValidationUtil.isFullCpf(userText) || aux.matches("[0-9]+")) {
                    userIsCpf = true;
                    if (ValidationUtil.isValidCpf(userText)) {
                        validCpf = true;
                        return true;
                    } else {
                        validCpf = false;
                        return false;
                    }
                } else {
                    userIsCpf = false;
                    if (ValidationUtil.isValidEmail(userText)) {
                        validEmail = true;
                        return true;
                    } else {
                        validEmail = false;
                        return false;
                    }
                }
            } else {
                userNotEmpty = false;
                return false;
            }
        } else {
            userNotEmpty = false;
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
