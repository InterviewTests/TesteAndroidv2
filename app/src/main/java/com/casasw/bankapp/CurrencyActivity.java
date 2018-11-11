package com.casasw.bankapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


interface CurrencyActivityInput {
    void displayCurrencyData(CurrencyViewModel viewModel);
}


public class CurrencyActivity extends AppCompatActivity
        implements CurrencyActivityInput {

    public static String TAG = CurrencyActivity.class.getSimpleName();
    CurrencyInteractorInput output;
    CurrencyRouter router;

    private TextView mUserName;
    private TextView mAccount;
    private TextView mBalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //do the setup
        setContentView(R.layout.currency_activity);


        CurrencyConfigurator.INSTANCE.configure(this);
        CurrencyRequest aCurrencyRequest = new CurrencyRequest();
        //populate the request


        output.fetchCurrencyData(aCurrencyRequest);
        // Do other work
    }


    @Override
    public void displayCurrencyData(CurrencyViewModel viewModel) {
        Log.e(TAG, "displayCurrencyData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data
    }
}
