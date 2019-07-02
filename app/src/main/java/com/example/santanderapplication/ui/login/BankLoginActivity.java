package com.example.santanderapplication.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.santanderapplication.R;
import com.example.santanderapplication.data.model.LoginRequestModel;
import com.example.santanderapplication.data.model.LoginResponseModel;
import com.example.santanderapplication.service.IApiLogin;
import com.example.santanderapplication.ui.transactions.BankCurrencyActivity;
import com.example.santanderapplication.service.ServiceRetrofit;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankLoginActivity extends AppCompatActivity implements BankLoginContract.View {

    private BankLoginContract.Presenter presenter;
    private Button buttonLogin;
    private EditText editTextLoginUser;
    private EditText editTextLoginPassword;
    private static final String USER_PREFERENCE="UserPreference";


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
            presenter.validateLogin( editTextLoginUser.getText().toString() ,
                    editTextLoginPassword.getText().toString() );

            if (!presenter.validatePassword( editTextLoginPassword.getText().toString() ))
                return;
            if(!presenter.validateUser( editTextLoginUser.getText().toString() ))
                return;


            login();
        }
    } );
        SharedPreferences preferences = getSharedPreferences( USER_PREFERENCE, 0 );
        String user = preferences.getString( "user","");
        editTextLoginUser.setText( user );


    }
    public void loadIU(){
        editTextLoginPassword=findViewById( R.id.edit_login_password );
        editTextLoginUser=findViewById( R.id.edit_login_user );
        buttonLogin=findViewById( R.id.button_login );
    }


    //Consumo API
    private void login (){
        IApiLogin apiLogin = ServiceRetrofit.createService( IApiLogin.class );
        Call<LoginResponseModel> loginResponseModelCall = apiLogin.postLoginApi( new LoginRequestModel(
                editTextLoginUser.getText().toString(), editTextLoginPassword.getText().toString()
        ) );
        loginResponseModelCall.enqueue( new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call,
                                   Response<LoginResponseModel> response) {
                if(response.isSuccessful()){
                    LoginResponseModel loginResponseModel = response.body();
                    if(loginResponseModel.getError().getCode()!= 0){
                        Toast.makeText( getApplicationContext(),
                                loginResponseModel.getError().getMessage(),
                                Toast.LENGTH_SHORT ).show();
                        return;
                    }

                    Intent intent = new Intent( BankLoginActivity.this,
                            BankCurrencyActivity.class );
                    intent.putExtra( "user",  loginResponseModel.getUserAccount() );
                    startActivity( intent );
                    finish();
                    return;
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Toast.makeText( getApplicationContext(), "Erro", Toast.LENGTH_SHORT ).show();
            }
        } );
    }


    public void savePreferences(){
        SharedPreferences preferences = getSharedPreferences( USER_PREFERENCE, 0 );
        SharedPreferences.Editor editor = preferences.edit();
        String user = editTextLoginUser.getText().toString();
        editor.putString( "user", user );
        editor.commit();
        editTextLoginUser.setText( user);
    }

    @Override
    public void showMessage(String error) {
        Toast.makeText( this, error, Toast.LENGTH_SHORT ).show();
    }
}
