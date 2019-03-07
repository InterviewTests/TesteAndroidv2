package br.com.kakobotasso.testeandroidv2.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import br.com.kakobotasso.testeandroidv2.R;
import br.com.kakobotasso.testeandroidv2.util.ScreenKeys;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        LoginConfigurator.INSTANCE.configure(this);
    }

    @OnClick(R.id.bt_login)
    public void tryLogin() {
        LoginRequest request = new LoginRequest();
        request.setUser(etUser.getText().toString());
        request.setPassword(etPassword.getText().toString());

        output.fetchLoginData(request);
    }

    @Override
    public void displayLoginError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
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
}
