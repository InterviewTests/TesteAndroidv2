package com.example.santanderapplication.ui.transactions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.santanderapplication.data.UserAccount;
import com.example.santanderapplication.data.model.StatementsModel;
import com.example.santanderapplication.data.model.StatementsResponseModel;
import com.example.santanderapplication.ui.login.BankLoginActivity;
import com.example.santanderapplication.R;

import java.text.NumberFormat;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BankCurrencyActivity extends AppCompatActivity implements BankCurrencyContract.StatementView {

    private RecyclerView recyclerViewBankCurrency;
    private ImageView imageViewExitBankCurrency;
    private TextView textViewNameBankCurrency;
    private TextView textViewAccountBankCurrency;
    private TextView textViewBalanceBankCurrency;
    private TextView textViewAgencyBankCurrency;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bank_currency );
        LoadIU();
        UserAccount user = (UserAccount) getIntent().getSerializableExtra( "user" );

        textViewNameBankCurrency.setText( user.getName() );
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String format = nf.format( user.getBalance() );

        textViewBalanceBankCurrency.setText( format );

        textViewAccountBankCurrency.setText( user.getBankAccount()
                + " / " + user.getAgency().replaceAll(
                "([0-9]{2})([0-9]{6})([0-9])", "$1.$2-$3" ) );


        BankCurrencyContract.StatementPresenter presenter = new BankCurrencyPresenter( this );

        retrofit = new Retrofit.Builder()
                .baseUrl( " https://bank-app-test.herokuapp.com/api/" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        presenter.eatingCurrency( user.getId() );


        imageViewExitBankCurrency.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogin();
            }
        } );
    }

    public void LoadIU() {
        recyclerViewBankCurrency = findViewById( R.id.recyclerView_bank_currency );
        imageViewExitBankCurrency = findViewById( R.id.imageview_exit_bank_currency );
        textViewAccountBankCurrency = findViewById( R.id.text_account_bank_currency );
        textViewBalanceBankCurrency = findViewById( R.id.text_balance_bank_currency );
        textViewNameBankCurrency = findViewById( R.id.text_name_bank_currency );
        textViewAgencyBankCurrency = findViewById( R.id.text_agency_bank_currncy );


    }

    public void showLogin() {
        Intent intent = new Intent( BankCurrencyActivity.this,
                BankLoginActivity.class );
        startActivity( intent );
        finish();
    }


    private void returnAdapter(List<StatementsModel> statementsModelList) {
        AdapterBankCurrency adapter = new AdapterBankCurrency( BankCurrencyActivity.this,
                statementsModelList );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                ( getApplicationContext() );
        recyclerViewBankCurrency.setLayoutManager( layoutManager );
        recyclerViewBankCurrency.setHasFixedSize( true );
        recyclerViewBankCurrency.setAdapter( adapter );
    }

    @Override
    public void showMessage(String error) {
        Toast.makeText( this, error, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void loadList(StatementsResponseModel statementsResponseModel) {
        returnAdapter( statementsResponseModel.getStatementsModelList() );
    }
}
