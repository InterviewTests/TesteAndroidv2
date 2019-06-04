package com.project.personal.app_bank.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.personal.app_bank.API.RetrofitClient;
import com.project.personal.app_bank.R;
import com.project.personal.app_bank.adapters.RecentesAdapter;
import com.project.personal.app_bank.models.StatementList;
import com.project.personal.app_bank.models.Statements;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecentesFragment extends Fragment {
    RecyclerView listasRecyclerView;
    List<Statements> list;

    public RecentesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_recentes, container, false);

        //criação do RecyclerView - getView() só funciona no onCreateView
        listasRecyclerView = fragment.findViewById(R.id.recyclerViewList);
        listasRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listasRecyclerView.setItemAnimator(new DefaultItemAnimator());
        listasRecyclerView.setHasFixedSize(true);

        getListStatements();


        return fragment;
    }

    private void getListStatements(){
        Call<StatementList> statementListCall = new RetrofitClient().getListService().getList("1");

        statementListCall.enqueue(new Callback<StatementList>() {
            @Override
            public void onResponse(Call<StatementList> call, Response<StatementList> response) {

                //carrega a lista com os lançamentos
                StatementList statementList = response.body();
                list = statementList.getStatements();


                //O setAdapter precisa ficar ligado ao carregamento dos dados para não ser chamado para uma lista nula
                listasRecyclerView.setAdapter(new RecentesAdapter(list, getContext()));
            }

            @Override
            public void onFailure(Call<StatementList> call, Throwable t) {

            }
        });

    }

}
