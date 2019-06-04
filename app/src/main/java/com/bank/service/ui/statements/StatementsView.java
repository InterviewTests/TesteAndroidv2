package com.bank.service.ui.statements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import com.bank.service.R;
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

    private ConnectionChek internet;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);

        CollapsingToolbarLayout collapsing = findViewById(R.id.collapsing_toolbar);
        collapsing.setTitle(getTitle());

        loadRecView();

         presenter = new StatementsPresenter(this);
        //((StatementsPresenter) presenter).setView(this);

        if(checkInternet()){
           presenter.loadList();

        }else{
           int msgCode = 2;
           presenter.loadAlert( msgCode,  getApplicationContext());
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

    public void updateRecView(List<Statements> list) {

        Statements statements ;

        if(list != null) {
            for (int count = 0; count < list.size(); count++) {

               // Log.d(TAG, "V/updateRecView/getData=" + list.get(count).getData());

                statements = new Statements(
                        list.get(count).getTitle(),
                        list.get(count).getDesc(),
                        list.get(count).getDate(),
                        list.get(count).getValue()
                );


               stmList.add(statements);
            }

           // stmList.add(stm);
            mAdapter.notifyDataSetChanged();

        }else{
            Log.d(TAG, "V/updateRecView/ERROR" );
        }


    }

    public int checkRecView(){

        int contItens = 0;
        if (recyclerView.getAdapter() != null) {
            contItens = recyclerView.getAdapter().getItemCount();
        }
            return contItens;
    }

    public void updateAlert( int msgCode, Context context){

        Log.d(TAG," / updateAlert -> ERROR -> messageCode  = " +  messageCode);

    }

    @Override
    public void onClick(View view) {

        Intent IT;
        Context context = getApplicationContext();

        // add click aqui
    }



}
