package br.com.dpassos.bankandroid.login.screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;
import br.com.dpassos.bankandroid.BaseView;
import br.com.dpassos.bankandroid.R;
import br.com.dpassos.bankandroid.infra.Asynchronous;
import br.com.dpassos.bankandroid.login.business.Login;
import br.com.dpassos.bankandroid.login.business.LoginControl;
import br.com.dpassos.bankandroid.statements.screen.StatementActivity;

public class LoginActivity extends BaseView {

    public EditText loginUserInput;
    public EditText loginPasswordInput;
    public Button loginConfirmButton    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_actitivy);

        loginButton.setOnClickListener(v -> callLogin());
        LoginEmailMask loginEmailMask = new LoginEmailMask();
        loginEmailMask.setInput(loginUserInput);

        retrieveLoginData();
    }

    void retrieveLoginData() {
        LoginControl loginControl = new LoginControl();
        Asynchronous asynchronous = new Asynchronous();
        asynchronous.execute(() -> {
            Login login = loginControl.lastLogin();
            runOnUiSafe(()->showUserData(login));
        });
    }

    void showUserData(Login login) {
        if(login!= null) {
            loginUserInput.setText(login.user);
        }
    }

    void callLogin() {
        loginButton.setEnabled(false);
        Snackbar.make(getWindow().getDecorView(), getResources().getString(R.string.login_doing_login), Snackbar.LENGTH_LONG).show();
        String user = loginUserInput.getText().toString();
        String pass = loginPasswordInput.getText().toString();

        LoginControl loginControl = new LoginControl();
        Asynchronous asynchronous = new Asynchronous();
        asynchronous.execute(() -> {
            try {
                loginControl.login(user, pass);
                runOnUiSafe(()->loginOk());

            } catch (LoginControl.LoginException e) {
                runOnUiSafe(()->loginBack(e.status.toString()));
            }
        });
    }

    void loginOk() {
        loginBack(getResources().getString(R.string.login_login_done));
        startActivity(new Intent(this, StatementActivity.class));
    }

    void loginBack(String msg) {
        loginButton.setEnabled(true);
        Snackbar.make(loginButton, msg, Snackbar.LENGTH_SHORT).show();
    }
}
