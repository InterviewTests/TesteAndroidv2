package br.com.douglas.fukuhara.bank.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import br.com.douglas.fukuhara.bank.R;
import br.com.douglas.fukuhara.bank.login.Contract;
import br.com.douglas.fukuhara.bank.login.configurator.LoginConfigurator;
import br.com.douglas.fukuhara.bank.login.router.LoginRouter;
import br.com.douglas.fukuhara.bank.network.vo.UserAccount;

public class LoginActivity extends AppCompatActivity implements Contract.LoginActivityInput {

    private static final String TAG = LoginActivity.class.getSimpleName();

    // Variables that holds view information
    private EditText mEdtUser;
    private EditText mEdtPassword;
    private Button mBtnLogin;

    // Android Clean Code interfaces
    private Contract.LoginInteractorInput mOutput;
    private LoginRouter mRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setViews();

        // Setting the Configurator
        LoginConfigurator.configure(this);
    }

    private void setViews() {
        mEdtUser = findViewById(R.id.login_edt_user);
        mEdtPassword = findViewById(R.id.login_edt_pass);
        mBtnLogin = findViewById(R.id.login_btn_login);

        mBtnLogin.setOnClickListener(this::onLoginClickListener);
    }

    private void onLoginClickListener(View view) {
        String username = mEdtUser.getText().toString();
        String password = mEdtPassword.getText().toString();

        hideKeyboard(view);

        // Get the Username and Password information and pass them to Interator,
        // so that it can validated it
        mOutput.onLogin(username, password);
    }

    private void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public Contract.LoginInteractorInput getOutput() {
        return mOutput;
    }

    public void setOutput(Contract.LoginInteractorInput output) {
        mOutput = output;
    }

    public LoginRouter getRouter() {
        return mRouter;
    }

    public void setRouter(LoginRouter router) {
        mRouter = router;
    }

    @Override
    public void notifyResourceErrorToUser(int stringRes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(stringRes);
        builder.setPositiveButton(R.string.login_dialog_error_ok_btn, (dialogInterface, i) -> {});
        builder.create().show();
    }

    @Override
    public void notifyErrorToUser(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.login_dialog_error_ok_btn, (dialogInterface, i) -> {});
        builder.create().show();
    }

    @Override
    public void onSuccessfulLogin(UserAccount userAccount) {
        Intent intent = mRouter.determineNextScreen();
        mRouter.passDataToNextScene(userAccount, intent);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mOutput.disposeAll();
        super.onDestroy();
    }
}
