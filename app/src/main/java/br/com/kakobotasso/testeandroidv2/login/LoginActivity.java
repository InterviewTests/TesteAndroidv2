package br.com.kakobotasso.testeandroidv2.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.kakobotasso.testeandroidv2.R;
import br.com.kakobotasso.testeandroidv2.util.ScreenKeys;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

interface LoginActivityInput {
    void displayLoginError(String msg);

    void displayCurrency(LoginResponse response);
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    LoginInteractorInput output;
    LoginRouter router;

    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.pb_login)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        LoginConfigurator.INSTANCE.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideProgressBar();
    }

    @OnClick(R.id.bt_login)
    public void tryLogin() {
        showProgressBar();

        LoginRequest request = new LoginRequest();
        request.setUser(etUser.getText().toString());
        request.setPassword(etPassword.getText().toString());

        output.fetchLoginData(request);
    }

    @OnEditorAction(R.id.et_password)
    public boolean imeDone() {
        hideKeyboard();
        tryLogin();
        return true;
    }

    @Override
    public void displayLoginError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        hideProgressBar();
    }

    @Override
    public void displayCurrency(LoginResponse response) {
        Bundle bundle = new Bundle();
        bundle.putString(ScreenKeys.NAME, response.getName());
        bundle.putString(ScreenKeys.AGENCY, response.getAgency());
        bundle.putString(ScreenKeys.BANK_ACCOUNT, response.getBankAccount());
        bundle.putDouble(ScreenKeys.BALANCE, response.getBalance());
        bundle.putInt(ScreenKeys.USER_ID, response.getUserId());

        router.passDataToCurrencyScreen(bundle);
    }

    private void showProgressBar() {
        btLogin.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        btLogin.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
