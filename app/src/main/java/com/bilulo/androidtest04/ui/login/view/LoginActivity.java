package com.bilulo.androidtest04.ui.login.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bilulo.androidtest04.R;
import com.bilulo.androidtest04.components.UserEditText;
import com.bilulo.androidtest04.ui.login.configurator.LoginConfigurator;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.router.LoginRouter;
import com.bilulo.androidtest04.utils.SharedPreferencesUtil;
import com.bilulo.androidtest04.utils.ValidationUtil;

public class LoginActivity extends AppCompatActivity implements LoginContract.ActivityContract, View.OnClickListener {

    public LoginContract.InteractorContract interactor;
    public LoginRouter router;
    public SharedPreferencesUtil sharedPreferencesUtil;

    /*private Guideline horizontalGuideline10;
    private Guideline verticalGuideline25;
    private Guideline horizontalGuideline40;
    private Guideline verticalGuideline75;
    private Guideline horizontalGuideline85;
    private TextView tvTitle;*/
    private UserEditText edtUser;
    private EditText edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginConfigurator.configure(this);
        findViews();
        hideActionBar();
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
    }

    private void findViews() {
        /*horizontalGuideline10 = findViewById(R.id.horizontal_guideline10);
        horizontalGuideline40 = findViewById(R.id.horizontal_guideline40);
        horizontalGuideline85 = findViewById(R.id.horizontal_guideline85);
        verticalGuideline25 = findViewById(R.id.vertical_guideline25);
        verticalGuideline75 = findViewById(R.id.vertical_guideline75);
        tvTitle = findViewById(R.id.tv_title);*/
        edtUser = findViewById(R.id.edt_user);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(btnLogin)) {
            Editable userText = edtUser.getText();
            Editable passwordText = edtPassword.getText();
            if (isValidLogin(userText, passwordText))
                interactor.performLogin(userText.toString(), passwordText.toString());
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
                String aux = user.replace(".", "").replace("-","");
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

    private void showAlertDialog(CharSequence message) {
        String title = getString(R.string.validation_error_title);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void loginSucessful() {
        int x=2;
    }

    @Override
    public void loginError() {
        showAlertDialog(getString(R.string.login_server_error));
    }
}
