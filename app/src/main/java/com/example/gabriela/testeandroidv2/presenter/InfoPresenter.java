package com.example.gabriela.testeandroidv2.presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gabriela.testeandroidv2.R;
import com.example.gabriela.testeandroidv2.interfaces.IRetrofitCEP;
import com.example.gabriela.testeandroidv2.model.InfoRes;
import com.example.gabriela.testeandroidv2.model.StatementList;
import com.example.gabriela.testeandroidv2.view.adapter.AdapterTransactions;
import com.example.gabriela.testeandroidv2.view.contact.InfoInterface;
import com.example.gabriela.testeandroidv2.view.ui.InfoActivity;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoPresenter {

    Activity activity;
    InfoInterface view;

    public InfoPresenter(Activity activity, InfoInterface view) {
        this.activity = activity;
        this.view = view;
    }

    public void showTrasactions(final ListView listTransactions) {
        view.showProgress();
        final IRetrofitCEP infoUser = IRetrofitCEP.retrofit.create(IRetrofitCEP.class);
        final Call<InfoRes> call = infoUser.getInfoUser();

        call.enqueue(new Callback<InfoRes>() {
            @Override
            public void onResponse(Call<InfoRes> call, Response<InfoRes> response) {
                view.hideProgress();
                if(response.code() == HttpURLConnection.HTTP_OK){
                    InfoRes infoRes = response.body();
                    ArrayList<StatementList> teste = new ArrayList<StatementList>();

                    for ( StatementList statementList : infoRes.statementList){
                        Log.e("Statement", statementList.title);
                        if(statementList.title != null){
                            teste.add(statementList);
                        }




                    }
                    populaAdapter(teste, listTransactions);


                }else{
                    view.showToast("Ops! Falha code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<InfoRes> call, Throwable t) {
                view.hideProgress();
                view.showToast("Ops! Houve uma falha ao carregar transações, verifique sua internet");

            }
        });


    }

    public void populaAdapter(ArrayList<StatementList> statementList, ListView listTransactions){

        AdapterTransactions adapter = new AdapterTransactions((InfoActivity) activity, R.layout.item_list, statementList);
        listTransactions.setAdapter(adapter);
    }
}
