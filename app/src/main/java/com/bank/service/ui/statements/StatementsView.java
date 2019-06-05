package com.bank.service.ui.statements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.bank.service.R;
import com.bank.service.ui.statements.domain.model.StatementList;
import com.bank.service.utils.ConnectionChek;
import com.bank.service.ui.statements.domain.model.Statements;

public class StatementsView extends AppCompatActivity implements
                                                      IStatements.Views,
                                                      View.OnClickListener  {
    private final String TAG ="STATEMENTS";
    private static IStatements.Presenter presenter;
    private int messageCode = 4; // Error

    private List<Statements> stmList = new ArrayList<>();

    private RecyclerView recyclerView;
    private StatementsAdapter mAdapter;

    public SwipeRefreshLayout rvSwipeRefresh;
    public ConnectionChek internet;
    public ProgressBar msgProgressBar;
    public AppBarLayout app_bar;
   // public Toolbar toolbar;

    public CoordinatorLayout msgStatusAlert;
    public Button msgStatusButton;
    public TextView msgStatusText;

    public FloatingActionButton btnGoBackButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statements);

        CollapsingToolbarLayout collapsing = findViewById(R.id.collapsing_toolbar);
        collapsing.setTitle(getTitle());


        loadRecView();
        loadViews("",1); // loadViews

         presenter = new StatementsPresenter(this);
        //((StatementsPresenter) presenter).setView(this);

        if(checkInternet()){
           presenter.loadList();

        }else{
           int msgCode = 2;
           presenter.onError("",1 );
        }


    }

    @Override
    public void onResume() {
    super.onResume();

    }

    public boolean checkInternet(){

        internet = new ConnectionChek();

        if(internet.isNetworkAvailable(getApplicationContext())){
          //  Log.d(TAG, " V/checkInternet=SUCCESS") ;
            return true;
        }else{
           // Log.d(TAG, " V/checkInternet=ERROR") ;
            return false;
        }

    }


    public void loadRecView(){

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new StatementsAdapter(stmList);
        recyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        while (recyclerView.getItemDecorationCount() > 0) {
            recyclerView.removeItemDecorationAt(0);
        }

        recyclerView.setNestedScrollingEnabled(true);
        //Log.d(TAG,"V/loadRecView=SUCCESS " );
    }

    public void updateRecView(StatementList listObj) {

        Statements statements  ;

        for(Statements objStm : listObj.statementList){

           // Log.i(TAG,"getTitle = " + objStm.getTitle());
           // Log.i(TAG,"getDesc = " + objStm.getDesc());
           // Log.i(TAG,"getDate = " + objStm.getDate());
           // Log.i(TAG,"getValue = " + objStm.getValue());
           // Log.i(TAG,"------------------ ");

            statements = new Statements(
                    objStm.getTitle(),
                    objStm.getDesc(),
                    objStm.getDate(),
                    objStm.getValue()
            );

            stmList.add(statements);
        }

            // stmList.add(stm);
            mAdapter.notifyDataSetChanged();

    }

    public int checkRecView(){

        int contItens = 0;
        if (recyclerView.getAdapter() != null) {
            contItens = recyclerView.getAdapter().getItemCount();
        }
            return contItens;
    }

    public void loadViews(String message, int code){

        msgProgressBar = (ProgressBar) findViewById(R.id.msg_progress_bar);
        btnGoBackButton = (FloatingActionButton) findViewById(R.id.btn_goback);
        btnGoBackButton.setOnClickListener(this);

        rvSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.rvSwipeRefresh);
        app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        msgStatusAlert = (CoordinatorLayout) findViewById(R.id.msg_status_alert);
        //msgStatusText = (TextView) findViewById(R.id.status_text);
        msgStatusButton = (Button) findViewById(R.id.msg_status_button);
        msgStatusButton.setOnClickListener(this);



        rvSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            int rvcounter = 1;

            @Override
            public void onRefresh() {


                //rvcounter++;
               // mAdapter.notifyDataSetChanged();
                presenter.loadList();
               // rvSwipeRefresh.setRefreshing(false);

               // Log.e(TAG, "CONTADOR = " + rvcounter);
            }


        });


        if(code==1) {

            app_bar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            msgStatusAlert.setVisibility(View.GONE);
            msgStatusButton.setText("");
            rvSwipeRefresh.setRefreshing(false);
            mAdapter.notifyDataSetChanged();

            msgProgressBar.setVisibility(recyclerView.GONE);

        }else{

            app_bar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            msgStatusAlert.setVisibility(View.VISIBLE);
            msgStatusButton.setText("Tentar Conectar");

            msgStatusButton.setVisibility(View.VISIBLE);
            msgProgressBar.setVisibility(View.GONE);

           // mAdapter.

        }

        Log.d(TAG,"V/loadViews/code=" +  code);

    }

    @Override
    public void onClick(View view) {

       // Context context = getApplicationContext();

        switch (view.getId()){

            case R.id.msg_status_button:

                //msgStatusButton.setText("Carregando...");
                msgStatusButton.setVisibility(View.GONE);
                msgProgressBar.setVisibility(View.VISIBLE);
                presenter.loadList();

              break;
            case R.id.btn_goback:

                Log.i(TAG,"SAINDO....");
                break;

        }

    }



}
