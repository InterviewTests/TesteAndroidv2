package br.com.fernandodutra.testeandroidv2.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.models.Login;
import br.com.fernandodutra.testeandroidv2.utils.Constants;
import br.com.fernandodutra.testeandroidv2.utils.ToolPermissions;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 17/06/2019
 * Time: 16:28
 * TesteAndroidv2
 */
public class UserAccountActivity extends AppCompatActivity implements UserAccountContract.View {

    private Context context;
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;

    private Login login;
    private UserAccountContract.Presenter userAccountPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        setupPermissions();
        setupComponents();
        setupActions();
    }

    private void setupPermissions() {
        // Request Permissions
        String[] permissoes = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.SET_DEBUG_APP
        };
        ToolPermissions.validate(this, 0, permissoes);
    }

    private void setupActions() {
        btn_login.setOnClickListener(setOnClickListenerLogin());
    }

    private void setupComponents() {
        context = UserAccountActivity.this;

        login = new Login();

        et_username = findViewById(R.id.login_activity_et_username);
        et_password = findViewById(R.id.login_activity_et_password);
        btn_login = findViewById(R.id.login_activity_btn_login);

        et_username.setText("fernando@gmail.com");
        et_password.setText("@Fer123");

        login.setUser(et_username.getText().toString().trim());
        login.setPassword(et_password.getText().toString().trim());

        userAccountPresenter = new UserAccountPresenter(this, new UserAccountModel(context));
    }

    private View.OnClickListener setOnClickListenerLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setUser(et_username.getText().toString().trim());
                login.setPassword(et_password.getText().toString().trim());

                userAccountPresenter.login(login);
            }
        };
    }

    @Override
    public void showMessage(int response) {
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String response) {
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigate(Integer userId) {
        Intent intent = new Intent(getBaseContext(), StatementListActivity.class);
        intent.putExtra(Constants.USERACCOUNT_USERID, userId);
        startActivity(intent);
        finish();
    }
}

