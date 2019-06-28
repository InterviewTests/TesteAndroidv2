package com.example.santandertestebank.model.repository;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.santandertestebank.model.models.ObjectsStatements;
import com.example.santandertestebank.model.service.ApiService;
import com.example.santandertestebank.ui.adapter.AdapterBankPayments;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BankPaymentsRepository {

    public void bringList() {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl (ApiService.BASE_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();

        ApiService service = retrofit.create (ApiService.class);
        final Call<ObjectsStatements> requestStatements = service.bringStatements (3);

        requestStatements.enqueue (new Callback<ObjectsStatements> () {
            @Override
            public void onResponse(Call<ObjectsStatements> call, Response<ObjectsStatements> response) {
                if (!response.isSuccessful ()) {
                    Toast.makeText (getApplicationContext (), "Erro: " + response.code (),
                            Toast.LENGTH_LONG).show ();
                } else {

                    ObjectsStatements statements = response.body ();

                    if (statements != null) {
                        adapter = new AdapterBankPayments (statements.getPaymentsStatements ());
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                            (getApplicationContext ());

                    recyclerViewPayments.setLayoutManager (layoutManager);
                    recyclerViewPayments.setHasFixedSize (true);
                    recyclerViewPayments.setAdapter (adapter);
                }
            }

            @Override
            public void onFailure(Call<ObjectsStatements> call, Throwable t) {
                Toast.makeText (getApplicationContext (), "Erro: " + t.getMessage (),
                        Toast.LENGTH_LONG).show ();
            }
        });

    }

}
