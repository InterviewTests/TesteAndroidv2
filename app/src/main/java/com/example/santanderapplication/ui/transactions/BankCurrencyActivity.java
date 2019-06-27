package com.example.santanderapplication.ui.transactions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.santanderapplication.data.model.LoginRequestModel;
import com.example.santanderapplication.data.model.LoginResponseModel;
import com.example.santanderapplication.data.model.StatementsModel;
import com.example.santanderapplication.data.model.StatementsResponseModel;
import com.example.santanderapplication.service.IApiCurrency;
import com.example.santanderapplication.service.IApiLogin;
import com.example.santanderapplication.service.ServiceRetrofit;
import com.example.santanderapplication.ui.login.BankLoginActivity;
import com.example.santanderapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BankCurrencyActivity extends AppCompatActivity implements BankCurrencyContract.StatementView  {

    private RecyclerView recyclerViewBankCurrency;
    private ImageView imageViewExitBankCurrency;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bank_currency );
        LoadIU();
        //consumeRetrofit( 1 );
        BankCurrencyContract.StatementPresenter presenter = new BankCurrencyPresenter();

        retrofit = new Retrofit.Builder()
                .baseUrl( " https://bank-app-test.herokuapp.com/api/" )
                .addConverterFactory( GsonConverterFactory.create())
                .build();

        imageViewExitBankCurrency.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogin();
            }
        } );
    }
    public void LoadIU(){
        recyclerViewBankCurrency=findViewById( R.id.recyclerView_bank_currency );
        imageViewExitBankCurrency=findViewById( R.id.imageview_exit_bank_currency );
    }

    public void showLogin (){
        Intent intent = new Intent( BankCurrencyActivity.this,
                BankLoginActivity.class );
        startActivity( intent );
    }

    public void consumeRetrofit (int Id){
        IApiCurrency apiCurrency = ServiceRetrofit.createService( IApiCurrency.class );
        Call<StatementsResponseModel> statementsResponseModelCall = apiCurrency.getStatementsApi(Id);
        statementsResponseModelCall.enqueue( new Callback<StatementsResponseModel>() {
            @Override
            public void onResponse(Call<StatementsResponseModel> call,
                                   Response<StatementsResponseModel> response) {
                if(response.isSuccessful()){
                    StatementsResponseModel statementsResponseModel = response.body();
                    returnAdapter(statementsResponseModel.getStatementsModelList());
                }
            }
            @Override
            public void onFailure(Call<StatementsResponseModel> call, Throwable t) {
                Toast.makeText( getApplicationContext(), "Erro", Toast.LENGTH_SHORT ).show();
            }
        });
    }

    private void returnAdapter(List<StatementsModel> statementsModelList) {
        AdapterBankCurrency adapter = new AdapterBankCurrency(BankCurrencyActivity.this ,
                statementsModelList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                ( getApplicationContext() );
        recyclerViewBankCurrency.setLayoutManager( layoutManager );
        recyclerViewBankCurrency.setHasFixedSize( true );
        recyclerViewBankCurrency.setAdapter( adapter );
    }
}
