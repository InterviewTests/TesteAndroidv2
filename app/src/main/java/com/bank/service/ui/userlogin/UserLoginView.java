package com.bank.service.ui.userlogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bank.service.R;
import com.bank.service.ui.statements.StatementsAdapter;
import com.bank.service.ui.statements.domain.model.Statements;
import com.bank.service.utils.ConnectionChek;

import java.util.ArrayList;
import java.util.List;



public class UserLoginView extends AppCompatActivity implements
                                                    ILogin.Views,
                                                    View.OnClickListener  {
    private final String TAG ="USERLOGIN";
    private static ILogin.Presenter presenter;
    private int messageCode = 4; // Error

    private List<Statements> stmList = new ArrayList<>();

    private RecyclerView recyclerView;
    private StatementsAdapter mAdapter;

    private ConnectionChek internet;
    public ProgressBar progressBar;
    public Toolbar toolbar;

    public CollapsingToolbarLayout collapsing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        collapsing = findViewById(R.id.collapsing_toolbar);
        collapsing.setTitle(getTitle());

        loadLogin();

        presenter = new UserLoginPresenter(this);
        //((StatementsPresenter) presenter).setView(this);

        // presenter.loadLogin();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setContentInsetsAbsolute(1,toolbar.getContentInsetStartWithNavigation());

    }

    @Override
    public void onResume() {
        super.onResume();

    }


    public void loadLogin(){

    }

    public void updateLogin(List<Statements> list) {

        Statements statements ;

        if(list != null) {
            for (int count = 0; count < list.size(); count++) {

                // Log.d(TAG, "V/updateRecView/getData=" + list.get(count).getData());

            }



        }else{
            Log.d(TAG, "V/updateLogin/ERROR" );
        }


    }

    public boolean checkLogin(){

        boolean contItens= false;

        Log.d(TAG,"V/checkLogin/contItens/" +  contItens);
        return contItens;
    }

    public void updateAlert( int msgCode, Context context){
        Log.d(TAG,"V/updateAlert/ERROR/messageCode/" +  messageCode);
    }

    @Override
    public void onClick(View view) {

        Intent IT;
        Context context = getApplicationContext();
        // add click aqui
    }



}
