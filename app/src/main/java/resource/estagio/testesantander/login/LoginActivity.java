package resource.estagio.testesantander.login;

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

import resource.estagio.testesantander.R;
import resource.estagio.testesantander.model.User;
import resource.estagio.testesantander.statement.StatementActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    public static final String Helvetica_Neue_Light = "HelveticaNeue-Light.otf";
    public static final String LoginPreferences = "LoginPreferences" ;



    private Button botao;
    private EditText username, password;
    private TextInputLayout textInputLayout, textInputLayout2;

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
        presenter = new LoginPresenter(this);
        setFonts();

        //Shared Preferences
        final SharedPreferences sharedPreferences = getSharedPreferences(LoginPreferences, Context.MODE_PRIVATE);
        String strusername = sharedPreferences.getString("username", "");
        username.setText(strusername);


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
                editor.putString("username", username.getText().toString());
                editor.commit();



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
        finish();
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
        textInputLayout.setTypeface(font);
        textInputLayout2.setTypeface(font);
    }


}
