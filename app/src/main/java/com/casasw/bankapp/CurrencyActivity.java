package com.casasw.bankapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;


interface CurrencyActivityInput {
    void displayCurrencyData(ArrayList<CurrencyModel> listOfStatements);
}


public class CurrencyActivity extends AppCompatActivity
        implements CurrencyActivityInput {

    public static String TAG = CurrencyActivity.class.getSimpleName();
    CurrencyInteractorInput output;
    CurrencyRouter router;
    private LoginViewModel mLoginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //do the setup
        setContentView(R.layout.currency_activity);
        mLoginModel = getIntent().getParcelableExtra("EXTRA_LOGIN_MODEL");

        CurrencyConfigurator.INSTANCE.configure(this);
        CurrencyRequest aCurrencyRequest = new CurrencyRequest(mLoginModel, getApplicationContext());
        //populate the request


        output.fetchCurrencyData(aCurrencyRequest);
        // Do other work
    }


    @Override
    public void displayCurrencyData(ArrayList<CurrencyModel> listOfStatements) {
        Log.e(TAG, "displayCurrencyData() called with: listOfStatements = [" + listOfStatements.toString() + "]");
        // Deal with the data
        CurrencyViewModel viewModel = new CurrencyViewModel(listOfStatements);
        Bundle args = new Bundle();
        args.putParcelable("EXTRA_LOGIN", mLoginModel);
        args.putParcelable("EXTRA_STATEMENTS", viewModel);
        CurrencyFragment fragment = new CurrencyFragment();
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();

    }
}
