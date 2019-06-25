package com.example.santanderapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;

import com.example.santanderapplication.BankLoginActivity;
import com.example.santanderapplication.R;
import com.example.santanderapplication.activity.adapter.AdapterBankCurrency;

public class BankCurrencyActivity extends AppCompatActivity  {

    private RecyclerView recyclerViewBankCurrency;
    private ImageView imageViewExitBankCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bank_currency );
        LoadIU();


        //configurar adapter
        AdapterBankCurrency adapter = new AdapterBankCurrency();

        //configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                ( getApplicationContext() );
        recyclerViewBankCurrency.setLayoutManager( layoutManager );
        recyclerViewBankCurrency.setHasFixedSize( true );
        recyclerViewBankCurrency.setAdapter( adapter );

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

}
