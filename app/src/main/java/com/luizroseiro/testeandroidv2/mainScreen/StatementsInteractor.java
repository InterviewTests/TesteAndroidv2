package com.luizroseiro.testeandroidv2.mainScreen;

import android.support.annotation.NonNull;

import com.luizroseiro.testeandroidv2.MainActivity;
import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.utils.DataService;
import com.luizroseiro.testeandroidv2.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface StatementsInteractorInput {
    void fetchStatements(StatementsRequest statementsRequest);
}

public class StatementsInteractor implements StatementsInteractorInput {

    private StatementsPresenterInput output;

    StatementsInteractor() {
        this.output = new StatementsPresenter();
    }

    @Override
    public void fetchStatements(StatementsRequest statementsRequest) {
        DataService.fetchStatements(statementsRequest.getId(), new Callback<StatementsResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatementsResponse> call,
                                   @NonNull Response<StatementsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    output.presentStatementsMetaData(response.body());
                }
                else
                    Utils.createToast(MainActivity.getContext()
                            .getString(R.string.error_statements));
            }

            @Override
            public void onFailure(@NonNull Call<StatementsResponse> call, @NonNull Throwable t) {
                Utils.createToast(MainActivity.getContext().getString(R.string.error_statements));
            }
        });
    }

}