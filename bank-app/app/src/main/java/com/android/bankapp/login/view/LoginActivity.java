package com.android.bankapp.login.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.bankapp.R;
import com.android.bankapp.login.LoginConfigurator;
import com.android.bankapp.login.interactor.LoginInteractorInput;
import com.android.bankapp.login.router.LoginRouterInput;
import com.pixplicity.easyprefs.library.Prefs;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.UnsupportedEncodingException;

import static com.android.bankapp.login.presenter.LoginPresenter.FIELDS_REQUIRED;
import static com.android.bankapp.login.presenter.LoginPresenter.LOGIN_UNAUTHORIZED;
import static com.android.bankapp.login.presenter.LoginPresenter.PASSWORD_INVALID;
import static com.android.bankapp.login.presenter.LoginPresenter.PASSWORD_KEY;
import static com.android.bankapp.login.presenter.LoginPresenter.REQUEST_ERROR;
import static com.android.bankapp.login.presenter.LoginPresenter.USER_KEY;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    private static final String TAG = "LoginActivity";
    @ViewById(R.id.edit_text_password)
    EditText editTextPassword;

    @ViewById(R.id.edit_text_user)
    EditText editTextUser;

    @ViewById(R.id.progress)
    ProgressBar progressBar;

    @ViewById(R.id.layout_login_fields)
    LinearLayout loginContent;

    public LoginInteractorInput output;
    public LoginRouterInput router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginConfigurator.INSTANCE.configure(this);
    }

    @AfterViews
    void afterViews(){
        isThereUserSaved();
    }

    private void isThereUserSaved() {

        String user = Prefs.getString(USER_KEY, "");
        String password = Prefs.getString(PASSWORD_KEY, "");

        try {
            byte[] data = Base64.decode(user, Base64.DEFAULT);
            String userDecoded = new String(data, "UTF-8");

            data = Base64.decode(password, Base64.DEFAULT);
            String passwordDecoded = new String(data, "UTF-8");

            editTextPassword.setText(passwordDecoded);
            editTextUser.setText(userDecoded);

        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public void loginSuccess() {
        router.goToNextScreen(this);
        finish();
    }

    @Override
    public void loginError(int code) {
        showContentState();
        switch (code) {
            case FIELDS_REQUIRED:
                Toast.makeText(this, getString(R.string.login_fields_required), Toast.LENGTH_LONG).show();
                break;
            case REQUEST_ERROR:
                Toast.makeText(this, getString(R.string.request_error), Toast.LENGTH_LONG).show();
                break;
            case PASSWORD_INVALID:
                Toast.makeText(this, getString(R.string.password_invalid), Toast.LENGTH_LONG).show();
                break;
            case LOGIN_UNAUTHORIZED:
                Toast.makeText(this, getString(R.string.login_unauthorize), Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, getString(R.string.request_error), Toast.LENGTH_LONG).show();
                Log.e(TAG, "Unexpected code " + code);
                break;
        }
    }

    public void doLogin(View view) {
        if (isNetworkConnection()) {
            showProgressState();
            output.doLogin(editTextUser.getText().toString(), editTextPassword.getText().toString());
        } else {
            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;

    }

    private void showContentState() {
        loginContent.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private void showProgressState() {
        loginContent.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }
}
