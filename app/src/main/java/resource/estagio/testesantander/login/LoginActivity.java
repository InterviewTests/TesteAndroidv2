package resource.estagio.testesantander.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import resource.estagio.testesantander.R;
import resource.estagio.testesantander.domain.User;
import resource.estagio.testesantander.statement.StatementActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    public static final String Helvetica_Neue_Light = "HelveticaNeue-Light.otf";
    public static final String LoginPreferences = "LoginPreferences" ;

    private Button botao;
    private EditText username, password;
    private TextInputLayout textInputLayout, textInputLayout2;
    private ProgressBar progressBar;

    public LoginContract.Presenter presenter;
    private LoginResponse loginResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout2 = findViewById(R.id.textInputLayout2);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        botao = findViewById(R.id.buttonSignUp);
        progressBar = findViewById(R.id.progressLogin);

        presenter = new LoginPresenter(this);//inicializa a presenter
        setFonts(); // implementa a fonte externa implementada na pasta "Assets"

        //Shared Preferences
        final SharedPreferences sharedPreferences = getSharedPreferences(LoginPreferences, Context.MODE_PRIVATE);
        String strusername = sharedPreferences.getString("username", "");
        username.setText(strusername);//se tiver algum login salvo, o "username" salvo é mostrado no EditText


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


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.validPassword(password.getText().toString())) {
                    presenter.login(username.getText().toString(), password.getText().toString());
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username.getText().toString());//insere o "username" inserido na tela de Login
                editor.commit();//salva o dado "username"



            }
        });

    }

    @Override
    public void navigateToList(User user) {//intent para a tela de Statement

        Intent intent = new Intent(LoginActivity.this, StatementActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();

    }

    public Context getContext() {
        return this;
    }//pega o contexto da activity

    public Context getActivity() {
        return this;
    }//pega o contexto da activity

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

    @Override
    public void showProgress(final boolean show) { // metodo do progress bar da tela de Login
        int shortAnimTime = getResources ().getInteger (android.R.integer.config_shortAnimTime);

        botao.setVisibility (show ? View.INVISIBLE : View.VISIBLE);
        botao.animate ().setDuration (shortAnimTime).alpha (
                show ? 0 : 1).setListener (new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                botao.setVisibility (show ? View.INVISIBLE : View.VISIBLE);
            }
        });

        progressBar.setVisibility (show ? View.VISIBLE : View.GONE);
        progressBar.animate ().setDuration (shortAnimTime).alpha (
                show ? 1 : 0).setListener (new AnimatorListenerAdapter () {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBar.setVisibility (show ? View.VISIBLE : View.GONE);
            }
        });

    }

    public void setFonts(){
        Typeface font = Typeface.createFromAsset(getAssets(), Helvetica_Neue_Light );
        username.setTypeface(font);
        password.setTypeface(font);
        botao.setTypeface(font);
        textInputLayout.setTypeface(font);
        textInputLayout2.setTypeface(font);
    }


}
