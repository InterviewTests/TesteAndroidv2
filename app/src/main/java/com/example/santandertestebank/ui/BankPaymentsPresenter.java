package com.example.santandertestebank.ui;

import com.example.santandertestebank.model.models.ObjectsStatements;
import com.example.santandertestebank.model.repository.BankPaymentsRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankPaymentsPresenter implements BankPaymentsContract.Presenter {

    private BankPaymentsContract.View view;

    public BankPaymentsPresenter(BankPaymentsContract.View view) {
        this.view = view;
    }

    @Override
    public void bringStatements(long id) {

        BankPaymentsRepository bankPaymentsRepository = new BankPaymentsRepository ();
        final Call<ObjectsStatements> requestStatements = bankPaymentsRepository.bringStatements (id);
        requestStatements.enqueue (new Callback<ObjectsStatements> () {
            @Override
            public void onResponse(Call<ObjectsStatements> call, Response<ObjectsStatements> response) {
                if (!response.isSuccessful ()) {
                    view.showToast (response.body ().getErrorStatement ().getMessage ());
                } else {

                    ObjectsStatements statements = response.body ();

                    if (statements != null) {
                        view.showUserinfos (statements);
                    }
                }
            }

            @Override
            public void onFailure(Call<ObjectsStatements> call, Throwable t) {
                view.showToast (t.getMessage ());
            }
        });

    }

}
