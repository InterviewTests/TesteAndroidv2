package resource.estagio.testesantander.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;

import resource.estagio.testesantander.R;
import resource.estagio.testesantander.model.User;
import resource.estagio.testesantander.statement.StatementActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    public static final String Helvetica_Neue_Light = "helvetica_neue_light.otf";
    private Button botao;
    private EditText username, password;

    public LoginContract.Presenter presenter;
    private LoginResponse loginResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        botao = findViewById(R.id.buttonSignUp);
        presenter = new LoginPresenter(this);
        setFonts();

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    presenter.validUsername(username.getText().toString());
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    presenter.validPassword(password.getText().toString());
                }
            }
        });


        //userSign();

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.validPassword(password.getText().toString())) {
                    presenter.login(username.getText().toString(), password.getText().toString());
                }

            }
        });

    }


    //  "(?=.*[a-z])" +         //pelo menos 1 letra minuscula
    // "(?=.*[A-Z])" +   //pelo menos 1 letra maiuscula


    @Override
    public void navigateToList(User user) {

        Intent intent = new Intent(LoginActivity.this, StatementActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        //saveLogin(user.getName());

    }

    public Context getContext() {
        return this;
    }

    public Context getActivity() {
        return this;
    }

    @Override
    public void enableButton(boolean b) {
        botao.setEnabled(b);
    }

    @Override
    public void errorPassword(String s) {
        password.setError(s);
    }

    @Override
    public void errorUsername(String s) {
        username.setError(s);
    }

    public void setFonts(){
        Typeface font = Typeface.createFromAsset(getAssets(), Helvetica_Neue_Light );
        username.setTypeface(font);
        password.setTypeface(font);
        botao.setTypeface(font);
    }


}
