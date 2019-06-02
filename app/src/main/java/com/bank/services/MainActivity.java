package com.bank.services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bank.services.ui.statements.IStatementsService;
import com.bank.services.ui.statements.domain.model.StatementList;
import com.bank.services.ui.statements.domain.model.Statements;

import java.sql.Statement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public  final String TAG = "STATEMENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IStatementsService.URL_STATEMENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IStatementsService service = retrofit.create(IStatementsService.class);
        Call<StatementList> requestList = service.ListStatement();

        requestList.enqueue(new Callback<StatementList>() {
            @Override
            public void onResponse(Call<StatementList> call, Response<StatementList> response) {

                if(response.isSuccessful()){

                    StatementList statement = response.body();



                    Log.e(TAG, "statement-> " + (statement != null) );
                    //Log.e(TAG, "toString-> " + (statement.statementList.get(0).getTitle()) );

                    for(Statements stm : statement.statementList){

                        Log.e(TAG,"item = " + stm.getTitle());
                   }

                }else{

                    Log.e(TAG, "Erro ao caregar Statements!" + response.code() );

                }
            }


            @Override
            public void onFailure(Call<StatementList> call, Throwable t) {
                Log.e(TAG, "Erro ao caregar Statements!" );
            }
        });







    }
}
