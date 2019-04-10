package com.desafiosantander.Home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.desafiosantander.LoggedUser;
import com.desafiosantander.R;


interface HomeActivityInput {
    public void validadeUser(HomeViewModel viewModel);
    public void validadePassword(HomeViewModel viewModel);
    public void enableLogin();
    public void doLogin (LoggedUser loggedUser);
}


public class HomeActivity extends AppCompatActivity
        implements HomeActivityInput {

    public static String TAG = HomeActivity.class.getSimpleName();
    HomeInteractorInput output;
    HomeRouter router;
    EditText user;
    EditText password;

    final int mode = Activity.MODE_PRIVATE;
    final String MYPREFS = "MyPreferences";
    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor myEditor;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //do the setup
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        HomeConfigurator.INSTANCE.configure(this);
        loginButton();
        userPassword();

    }

    @Override
    public void validadeUser(HomeViewModel viewModel) {

        user.setError(viewModel.userValidation);
        login.setEnabled(false);

    }

    @Override
    public void validadePassword(HomeViewModel viewModel) {
        password.setError(viewModel.passwordValidation);
        login.setEnabled(false);
    }

    @Override
    public void enableLogin() {
        login.setEnabled(true);
    }


    @Override
    public void doLogin(LoggedUser loggedUser) {
        Log.d("Activity","Usuariologado: "+ loggedUser.name);

        myEditor = mySharedPreferences.edit();
        myEditor.putString("LastUser", user.getText().toString());
        myEditor.apply();

        router.login(loggedUser);

    }

    private void loginButton(){
        login = (Button) findViewById(R.id.btn_login);
        login.setClickable(true);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.doLogin(user.getText().toString(), password.getText().toString());
            }
        });
    }

    private void userPassword(){
        user = (EditText) findViewById(R.id.edt_login);
        password = (EditText) findViewById(R.id.edt_password);

        mySharedPreferences = getSharedPreferences(MYPREFS, 0);
        String lastUser = mySharedPreferences.getString("LastUser","");
        user.setText(lastUser);

        output.validadeUser(user);
        output.validadePassword(password);
    }

}
