package com.ivan.bankapp.view.presentation;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ivan.bankapp.R;
import com.ivan.bankapp.database.UserDB;
import com.ivan.bankapp.utils.PasswordStrength;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Login extends AppCompatActivity implements ViewContract.IView {

    public static EditText user, password;
    Button buttonLogin;
    public String username, userpassword;
    public RealmConfiguration realmConfig;
    public Realm realm;
    private ViewContract.IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.editUser);
        password = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        presenter = new Presenter(this);

        Realm.init(getApplicationContext());
        realmConfig = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(realmConfig);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields(user.getText().toString(), password.getText().toString());
            }
        });

    }

    private <ViewType extends View> ViewType find(@IdRes int viewId) {
        return (ViewType) findViewById(viewId);
    }

    private boolean validatePassword(String str){

        PasswordStrength passwordStrength = PasswordStrength.calculate(str);

        String strength = passwordStrength.name().toLowerCase();

        if(strength.equals("weak") || strength.equals("medium")) return false;
        else return true;

    }

    private void goHome(){

        if(validatePassword(password.getText().toString())) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Insira uma senha forte", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getStatements(String title, String desc, String data, Double value) {

    }

    @Override
    public void login(Integer id, String name, String account, String agency, Double value) {

        //Salvar no Realm

        UserDB userDB = new UserDB();

        userDB.setId(id);
        userDB.setName(name);
        userDB.setBankAccount(account);
        userDB.setAgency(agency);
        userDB.setBalance(value);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(userDB);
        realm.commitTransaction();

        goHome();

    }

    public static String getUsername(){
        String username = "";
        username = user.getText().toString();
        return username;
    }

    public static String getPassword(){
        String userpassword = "";
        userpassword = password.getText().toString();
        return userpassword;
    }

    private void validateFields(String username, String userpassword){

        if (username.isEmpty()) {
            user.setError("Necessário inserir um e-mail");
            user.requestFocus();
            return;
        }


        if (userpassword.isEmpty()) {
            password.setError("Por favor, insira sua senha!");
            password.requestFocus();
            return;
        }

        if (presenter != null) {
            presenter.onLogin();
        }
    }
}