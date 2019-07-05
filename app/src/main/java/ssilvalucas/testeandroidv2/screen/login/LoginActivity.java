package ssilvalucas.testeandroidv2.screen.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ssilvalucas.testeandroidv2.R;

interface LoginActivityInput{
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    private static final String TAG = LoginActivity.class.getSimpleName();

    protected LoginInteractorInput output;
    protected LoginRouter router;

    private EditText editTextUsername, editTextPassword;
    private Button   btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.screen_login_edit_text_user);
        editTextPassword = findViewById(R.id.screen_login_edit_text_password);

        btnLogin = findViewById(R.id.screen_login_btn);
        btnLogin.setOnClickListener(getOnClickListenerBtnLogin());

        LoginConfigurator.INSTANCE.configure(this);
        fetchMetaData();
    }

    public void fetchMetaData(){
        output.fetchLoginMetaData();
    }

    private View.OnClickListener getOnClickListenerBtnLogin(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                output.onLogin(username, password);
            }
        };
    }
}
