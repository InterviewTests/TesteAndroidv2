package com.example.santandertestebank.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.santandertestebank.R;
import com.example.santandertestebank.model.models.UserAccountLogin;
import com.example.santandertestebank.ui.BankPaymentsActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonLogin;

    private SharedPreferences sharedPref;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_USER = "user";

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        getSupportActionBar ().hide ();

        loadUI ();
        presenter = new LoginPresenter (this);

        buttonLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                presenter.loginUser (
                        editTextUser.getText ().toString (),
                        editTextPassword.getText ().toString ());
                saveData ();
            }
        });
    }

    public void saveData() {
        sharedPref = getSharedPreferences (SHARED_PREFS, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit ();
        editor.putString (KEY_USER, editTextUser.getText ().toString ());
        editor.apply ();
        editTextUser.setText (sharedPref.getString (KEY_USER, ""));
    }

    private void loadUI() {
        editTextUser = findViewById (R.id.edit_text_user);
        editTextPassword = findViewById (R.id.edit_text_password);
        buttonLogin = findViewById (R.id.button_login);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String s) {
        Toast.makeText (this, s, Toast.LENGTH_LONG).show ();
    }

    @Override
    public void showUserInfo(UserAccountLogin userAccountLogin) {
        Intent i = new Intent (getApplicationContext (), BankPaymentsActivity.class);
        i.putExtra ("keyLogin", userAccountLogin);
        startActivity (i);
        finish ();
    }
}

//    public void loginUser() {
//        Retrofit retrofit = new Retrofit.Builder ()
//                .baseUrl (ApiService.BASE_URL)
//                .addConverterFactory (GsonConverterFactory.create ())
//                .build ();
//
//        ApiService service = retrofit.create (ApiService.class);
//        final Call<ObjectsLogin> requestLogin = service.loginUSer ("tesetei2", "Test@1");
//
//        requestLogin.enqueue (new Callback<ObjectsLogin> () {
//            @Override
//            public void onResponse(Call<ObjectsLogin> call, Response<ObjectsLogin> response) {
//
//                if (!response.isSuccessful ()) {
//                    Toast.makeText (getApplicationContext (), "Erro: " + response.code (),
//                            Toast.LENGTH_LONG).show ();
//                } else {
//
//                    ObjectsLogin login = response.body ();
//                    login.getUserAccountLogin ().getUserId ();
//
//                    Intent i = new Intent (getApplicationContext (), BankPaymentsActivity.class);
//                    i.putExtra ("keyLogin", login.getUserAccountLogin ());
//                    startActivity (i);
//                    finish ();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ObjectsLogin> call, Throwable t) {
//                Toast.makeText (getApplicationContext (), "Erro: " + t, Toast.LENGTH_LONG).show ();
//
//            }
//        });
//    }