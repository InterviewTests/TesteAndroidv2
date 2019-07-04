package com.example.santanderapplication.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.santanderapplication.R;
import com.example.santanderapplication.data.model.LoginResponseModel;
import com.example.santanderapplication.ui.transactions.BankCurrencyActivity;

public class BankLoginActivity extends AppCompatActivity implements BankLoginContract.View {

    private BankLoginContract.Presenter presenter;
    private Button buttonLogin;
    private EditText editTextLoginUser;
    private EditText editTextLoginPassword;
    private ProgressBar progressBarLoginBank;
    private static final String USER_PREFERENCE = "UserPreference";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bank_login );
        loadIU();


        presenter = new BankLoginPresenter( this );


        buttonLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savePreferences();


                presenter.validateLogin( editTextLoginUser.getText().toString(),
                        editTextLoginPassword.getText().toString() );

                if (!presenter.validatePassword( editTextLoginPassword.getText().toString() ))
                    return;
                if (!presenter.validateUser( editTextLoginUser.getText().toString() ))
                    return;

                presenter.eatinglogin( editTextLoginUser.getText().toString(),
                        editTextLoginPassword.getText().toString() );
            }
        } );
        SharedPreferences preferences = getSharedPreferences( USER_PREFERENCE, 0 );
        String user = preferences.getString( "user", "" );
        editTextLoginUser.setText( user );


    }

    public void loadIU() {
        editTextLoginPassword = findViewById( R.id.edit_login_password );
        editTextLoginUser = findViewById( R.id.edit_login_user );
        buttonLogin = findViewById( R.id.button_login );
        progressBarLoginBank = findViewById( R.id.progressBar_login_bank );
    }


    public void savePreferences() {
        SharedPreferences preferences = getSharedPreferences( USER_PREFERENCE, 0 );
        SharedPreferences.Editor editor = preferences.edit();
        String user = editTextLoginUser.getText().toString();
        editor.putString( "user", user );
        editor.commit();
        editTextLoginUser.setText( user );
    }

    @Override
    public void showMessage(String error) {
        Toast.makeText( this, error, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void showActivity(LoginResponseModel loginResponseModel) {
        Intent intent = new Intent( BankLoginActivity.this, BankCurrencyActivity.class );
        intent.putExtra( "user", loginResponseModel.getUserAccount() );
        startActivity( intent );
        finish();

    }

    @Override
    public void showProgress(final boolean b) {
        int shortAnimTime = getResources().getInteger( android.R.integer.config_shortAnimTime );

        progressBarLoginBank.setVisibility( b ? View.INVISIBLE : View.VISIBLE );
        buttonLogin.animate().setDuration( shortAnimTime ).alpha(
                b ? 0 : 1 ).setListener( new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                buttonLogin.setVisibility( b ? View.INVISIBLE : View.VISIBLE );
            }
        } );

        progressBarLoginBank.setVisibility( b ? View.VISIBLE : View.GONE );
        progressBarLoginBank.animate().setDuration( shortAnimTime ).alpha(
                b ? 1 : 0 ).setListener( new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBarLoginBank.setVisibility( b ? View.VISIBLE : View.GONE );
            }
        } );
    }
}
