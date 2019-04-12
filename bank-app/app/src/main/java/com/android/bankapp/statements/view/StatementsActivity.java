package com.android.bankapp.statements.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.bankapp.R;
import com.android.bankapp.statements.StatementConfigurator;
import com.android.bankapp.statements.interactor.StatementInteractor;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@SuppressLint("Registered")
@EActivity(R.layout.activity_steatements)
public class StatementsActivity extends AppCompatActivity implements StatementActivityInput {


    public StatementInteractor output;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatementConfigurator.INSTANCE.configure(this);
    }

    @AfterViews
    void afterViews(){
        output.loadData();
    }

    @Override
    public void dataLoaded() {

    }

    @Override
    public void errorLoadData() {

    }
}
