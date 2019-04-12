package com.android.bankapp.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.bankapp.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static com.android.bankapp.login.LoginPresenter.LOGIN_UNAUTHORIZE;
import static com.android.bankapp.login.LoginPresenter.PASSWORD_INVALID;
import static com.android.bankapp.login.LoginPresenter.REQUEST_ERROR;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    private static final String TAG = "LoginActivity";
    @ViewById(R.id.edit_text_password)
    EditText editTextPassword;

    @ViewById(R.id.edit_text_user)
    EditText editTextUser;

    LoginInteractorInput output;
    LoginRouterInput router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginConfigurator.INSTANCE.configure(this);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError(int code) {
        switch (code) {
            case REQUEST_ERROR:
                Toast.makeText(this, getString(R.string.request_error), Toast.LENGTH_SHORT).show();
                break;
            case PASSWORD_INVALID:
                Toast.makeText(this, getString(R.string.password_invalid), Toast.LENGTH_SHORT).show();
                break;
            case LOGIN_UNAUTHORIZE:
                Toast.makeText(this, getString(R.string.login_unauthorize), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, getString(R.string.request_error), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Unexpected code " + code);
                break;
        }
    }

    public void doLogin(View view) {
        output.doLogin(editTextUser.getText().toString(), editTextPassword.getText().toString());
    }
}
