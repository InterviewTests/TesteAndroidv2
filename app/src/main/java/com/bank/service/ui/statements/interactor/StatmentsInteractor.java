package com.bank.service.ui.statements.interactor;


import android.icu.text.DateFormat;
import android.util.Log;

import com.bank.service.data.local.LoadLocalTest;
import com.google.gson.Gson;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.bank.service.ui.statements.IStatements;
import com.bank.service.ui.statements.IStatementsService;
import com.bank.service.ui.statements.domain.model.StatementList;
import com.bank.service.ui.statements.domain.model.Statements;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StatmentsInteractor implements IStatements.Interactor{

    public int MAX_CONNECTION_TENTATIVES = 5;
    public int CONNECTION_TENTATIVES = 0;

    public  final String TAG = "STATEMENTS";
    public IStatements.Presenter presenter;

    public List<StatementList> listStm = new ArrayList<>();
    public List<Statements> listItens = new ArrayList<>();


    public StatmentsInteractor(IStatements.Presenter presenter){

        //presenter = new StatementsPresenter();
        this.presenter = presenter;

    }


    public void onComplete(StatementList listGet){


        if(listGet!=null) {
            presenter.onSuccess(listGet);

        }else{
            presenter.onError("Erro ao carregar itens!",1);
        }
    }



    public List<Statements> loadList(String areaApp){
       // final List<StatementList> listData = new ArrayList<>();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //.callTimeout()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat(DateFormat.LONG)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IStatementsService.URL_STATEMENT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        IStatementsService service = retrofit.create(IStatementsService.class);
        Call<StatementList> requestList = service.getList();
        //StatementList<Statements> requestList = service.getStatements();

        requestList.enqueue(new Callback<StatementList>() {
            @Override
            //public void onResponse(Call<StatementList> call, Response<StatementList> response) {
            public void onResponse(Call<StatementList> call, Response<StatementList> response) {

                if(response.isSuccessful()){
                    try {
                        if(response.body()!=null)
                        {
                            StatementList listGet = response.body();
                           // Statements itemGet = response.body().getList();
                            onComplete(listGet);

                            listStm.add(listGet);

                        }else{
                            Log.e(TAG,"onResponse/ERRO/response.body()=nulo ");
                        }

                    }catch (Exception e){
                        Log.e(TAG,"onResponse/ERRO=" + e.getMessage());

                    }
                }else{
                    Log.e(TAG, "onResponse/ERRO=" + response.code() );
                }

            }
            @Override
            public void onFailure(Call<StatementList> call, Throwable t) {

                onError();

            }
        });

        return listItens;
    }


    public void onError(){

        CONNECTION_TENTATIVES = CONNECTION_TENTATIVES + 1;
        if(CONNECTION_TENTATIVES <= MAX_CONNECTION_TENTATIVES){

            loadList("statements");
            Log.i(TAG,"I/onError/Tentando conectar=" + CONNECTION_TENTATIVES );

        }else{
            presenter.onError("Erro ao carregar lista!",1);
        }


        if(CONNECTION_TENTATIVES > MAX_CONNECTION_TENTATIVES){

           // CONNECTION_TENTATIVES = 0;
        }
    }



    public List<Statements> loadListTest(String areaApp){

         List<Statements> listData = new ArrayList<>();
         String fileString;
         LoadLocalTest teste = new LoadLocalTest();

         fileString = teste.loadDataLocal(null); // carrega
         listData = teste.gsonToList(fileString); // converte

          //List<Statements> gsonTolistTest = new ArrayList<>();

        return  listData;
    }

    public void Teste(Statements statements){

        Log.e(TAG, "TESTEZ = " + statements.getTitle());
    }



 }
