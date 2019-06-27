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


        //SharedPreference
        savePreferences();
        SharedPreferences preferences = getSharedPreferences( USER_PREFERENCE, 0 );
        String user = preferences.getString( "user","");
        editTextLoginUser.setText( user );

        //Presenter
        presenter = new BankLoginPresenter( this );


        buttonLogin.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent( BankLoginActivity.this ,
                    BankCurrencyActivity.class );
            startActivity( intent );
        }
    } );

    }
    public void loadIU(){
        editTextLoginPassword=findViewById( R.id.edit_login_password );
        editTextLoginUser=findViewById( R.id.edit_login_user );
        buttonLogin=findViewById( R.id.button_login );
    }


    //Consumo API
    private void login (final LoginRequestModel loginRequestModel){
        IApiLogin apiLogin = ServiceRetrofit.createService( IApiLogin.class );
        Call<LoginResponseModel> loginResponseModelCall = apiLogin.postLoginApi( loginRequestModel );
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
                    Bundle bundle = new Bundle( );
                    bundle.putDouble( "Id" , loginResponseModel.getUserAccount().getId());
                    bundle.putString("Name", loginResponseModel.getUserAccount().getName());
                    bundle.putString("bankAccount", loginResponseModel.getUserAccount().getBankAccount());
                    bundle.putDouble("Balance", loginResponseModel.getUserAccount().getBalance());
                    bundle.putString("Agency", loginResponseModel.getUserAccount().getAgency());
                    intent.putExtras( bundle );
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

    //Método SharedPreference
    public void savePreferences(){
        SharedPreferences preferences = getSharedPreferences( USER_PREFERENCE, 0 );
        SharedPreferences.Editor editor = preferences.edit();
        String user = editTextLoginUser.getText().toString();
        editor.putString( "user", user );
        editor.commit();
        editTextLoginUser.setText( user);
    }


    //Mensagem de erro vindo da presenter

    @Override
    public void showMessage(String error) {
        Toast.makeText( this, "Erro", Toast.LENGTH_SHORT ).show();
    }
}
