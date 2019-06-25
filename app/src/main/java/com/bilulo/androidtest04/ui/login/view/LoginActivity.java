package com.bilulo.androidtest04.ui.login.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginConfigurator.configure(this);
        hideActionBar();
        findViews();
        loadUsername();
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
            Editable userText = edtUser.getText();
            Editable passwordText = edtPassword.getText();
            if (isValidLogin(userText, passwordText)) {
                showProgressDialog();
                interactor.performLogin(userText.toString(), passwordText.toString());
            }
        }
    }

    private boolean isValidLogin(Editable userText, Editable passwordText) {
        return isValidUser(userText) && isValidPassword(passwordText);
    }

    private boolean isValidPassword(Editable passwordText) {
        if (passwordText != null) {
            String password = passwordText.toString();
            if (ValidationUtil.isValidString(password)) {
                boolean hasUppercase = true;
                boolean hasSpecial = true;
                boolean hasAlphanumeric = true;
                if (!ValidationUtil.hasUppercase(password)) {
                    hasUppercase = false;
                }
                if (!ValidationUtil.hasSpecialCharacter(password)) {
                    hasSpecial = false;
                }
                if (!ValidationUtil.hasAlphanumericCharacter(password)) {
                    hasAlphanumeric = false;
                }
                if (!hasUppercase || !hasSpecial || !hasAlphanumeric) {
                    showAlertDialog(hasUppercase, hasSpecial, hasAlphanumeric);
                    return false;
                } else
                    return true;
            } else {
                showAlertDialog(getString(R.string.validation_error_empty_password));
                return false;
            }
        }
        showAlertDialog(getString(R.string.validation_error_empty_password));
        return false;
    }

    private boolean isValidUser(Editable userText) {
        if (userText != null) {
            String user = userText.toString();
            if (ValidationUtil.isValidString(user)) {
                String aux = user.replace(".", "").replace("-", "");
                if (ValidationUtil.isFullCpf(user) || aux.matches("[0-9]+")) {
                    if (ValidationUtil.isValidCpf(user)) {
                        return true;
                    } else {
                        showAlertDialog(getString(R.string.validation_error_user_cpf));
                        return false;
                    }
                } else {
                    if (ValidationUtil.isValidEmail(user)) {
                        return true;
                    } else {
                        showAlertDialog(getString(R.string.validation_error_user_email));
                        return false;
                    }
                }
            } else {
                showAlertDialog(getString(R.string.validation_error_empty_user));
                return false;
            }
        } else {
            showAlertDialog(getString(R.string.validation_error_empty_user));
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
