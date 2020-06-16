package com.testeandroidv2.statementScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.testeandroidv2.R;


interface StatementActivityInput {
    public void displayLoginData(StatementViewModel viewModel);
}


public class StatementActivity extends AppCompatActivity implements StatementActivityInput {

    public static String TAG = StatementActivity.class.getSimpleName();
    StatementInteractorInput output;
    StatementRouter router;
    StatementModel login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        StatementConfigurator.INSTANCE.configure(this);

        fetchMetaData();
    }

    public void fetchMetaData(){
        StatementRequest aStatementRequest = new StatementRequest();
        //populate the request
        output.fetchLoginData(aStatementRequest);
        // Do other work
    }


    @Override
    public void displayLoginData(StatementViewModel viewModel) {
        Log.e(TAG, "displayLoginData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data
    }
}
