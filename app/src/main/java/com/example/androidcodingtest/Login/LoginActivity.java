package com.example.androidcodingtest.Login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidcodingtest.R;
import com.example.androidcodingtest.models.Error;

public class LoginActivity extends AppCompatActivity implements LoginInteractor.View{

    LoginPresenter presenter = new LoginPresenter(this);
    SharedPreferences sharedPreferences;
    ProgressDialog dialog;
    LoginActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("PrefFileBankApp#", 0);

        setOnClickEvents();
        checkCachedUser();
    }

    private void checkCachedUser() {
        String userCached = sharedPreferences.getString("loginUser", null);
        if(userCached != null){
            EditText user = findViewById(R.id.user);
            user.setText(userCached);
        }
    }

    private void setOnClickEvents() {
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userField = findViewById(R.id.user);
                EditText passwordField = findViewById(R.id.password);

                String user = userField.getText().toString();
                String password = passwordField.getText().toString();

                dialog = ProgressDialog.show(activity, "",
                        getResources().getString(R.string.wait_dialog_message), true);
                presenter.login(user, password);
            }
        });
    }

    @Override
    public void loginSuccess(String user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("loginUser", user);
        editor.commit();

        dialog.dismiss();
    }

    @Override
    public void loginError(Error error) {
        if(error ==  null) {
            Toast.makeText(this, getResources().getString(R.string.default_error), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dialog.dismiss();
    }

    @Override
    public void loginError(int error) {
        Toast.makeText(this, getString(error), Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}
