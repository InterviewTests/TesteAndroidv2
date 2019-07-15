package br.com.fernandodutra.testeandroidv2.activities.login;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.models.ErrorMessage;
import br.com.fernandodutra.testeandroidv2.models.Login;
import br.com.fernandodutra.testeandroidv2.utils.ToolPermissions;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 26/06/2019
 * Time: 21:11
 * TesteAndroidv2_CleanCode
 */

interface LoginActivityInput {
    void displayLoginMetaData(LoginViewModel viewModel);
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    private Context context;
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    //
    LoginInteractorInput loginInteractorInput;
    LoginRouter loginRouter;
    //
    LoginViewModel viewModel;
    List<ErrorMessage> errorMessage;
    //
    public static String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        LoginConfigurator.INSTANCE.configure(this);

        setupPermissions();
        setupComponents();
        setupActions();
    }

    private void setupPermissions() {
        // Request Permissions
        String[] permissoes = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE
        };
        ToolPermissions.validate(this, 0, permissoes);
    }

    private void setupActions() {
        btn_login.setOnClickListener(setOnClickListenerLogin());
    }

    private View.OnClickListener setOnClickListenerLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchMetaData();
            }
        };
    }

    private void setupComponents() {
        context = LoginActivity.this;

        et_username = findViewById(R.id.login_activity_et_username);
        et_password = findViewById(R.id.login_activity_et_password);
        btn_login = findViewById(R.id.login_activity_btn_login);
    }

    public void fetchMetaData() {
        // create Request and set the needed input
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setLogin(new Login(et_username.getText().toString().trim(),
                et_password.getText().toString().trim()));

        // Call the output to fetch the data
        loginInteractorInput.fetchLoginMetaData(context, loginRequest);
    }

    @Override
    public void displayLoginMetaData(LoginViewModel viewModel) {
        this.viewModel = viewModel;
        if (viewModel.errorMessage.size() > 0) {
            StringBuilder listMessage = new StringBuilder();
            this.errorMessage = viewModel.errorMessage;

            for (ErrorMessage em : this.errorMessage)
                listMessage.append(getString(em.getMessageId()));

            this.showMessage(listMessage.toString());
        } else {
            loginRouter.statemetListRouter(viewModel.userAccountWorker.getUserAccount());
        }
    }

    public void showMessage(String response) {
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
    }
}
