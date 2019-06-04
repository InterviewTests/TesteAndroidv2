package com.bank.service.ui.statements.interactor;


import android.util.Log;

import com.bank.service.data.local.LoadLocalTest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import com.bank.service.ui.statements.IStatements;
import com.bank.service.ui.statements.IStatementsService;
import com.bank.service.ui.statements.domain.model.StatementList;
import com.bank.service.ui.statements.domain.model.Statements;

//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;


public class StatmentsInteractor implements IStatements.Interactor{

    public  final String TAG = "STATEMENTS";
    public IStatements.Presenter presenter;

    public List<StatementList> listStm = new ArrayList<>();



    public StatmentsInteractor(IStatements.Presenter presenter){

        //presenter = new StatementsPresenter();
        this.presenter = presenter;

    }



    /*

    public List<StatementList> loadDetail(String areaApp){

       // final List<StatementList> listData = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IStatementsService.URL_STATEMENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IStatementsService service = retrofit.create(IStatementsService.class);
        Call<StatementList> requestList = service.getStatements();
        //StatementList<Statements> requestList = service.getStatements();

        requestList.enqueue(new Callback<StatementList>() {
            @Override
            public void onResponse(Call<StatementList> call, Response<StatementList> response) {

                if(response.isSuccessful()){
                    try {
                        if(response.body()!=null)
                        {
                            StatementList statement = response.body();

                            Gson gson = new Gson();

                            IStatementsService SERV = gson
                                    .fromJson(response.errorBody().string(),
                                     IStatementsService.class);

                            int i = 0;
                            for(Statements stmOBj : statement.statementList){

                                Log.i(TAG,"TESTEX = " + stmOBj.getTitle());

                               i++;
                            }

                           // List<StatementList> tasks = call.execute().body();
                            listStm.add(response.body());
                            //listUpdate.addAll(response.body());

                            Log.e(TAG, "I/onResponse/listUpdate=" + (listStm.size() ) );

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
                Log.e(TAG, "onFailure/EERO="+ t.getStackTrace() );
            }
        });

        return listStm;
    }

*/



    public List<Statements> loadListTest(String areaApp){

         List<Statements> listData = new ArrayList<>();
         String fileString;
         LoadLocalTest teste = new LoadLocalTest();

         fileString = teste.loadDataLocal(null); // carrega
         listData = teste.gsonToList(fileString); // converte

          //List<Statements> gsonTolistTest = new ArrayList<>();

        return  listData;
    }
 }
