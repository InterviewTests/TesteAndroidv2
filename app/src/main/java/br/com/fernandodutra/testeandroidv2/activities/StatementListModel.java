package br.com.fernandodutra.testeandroidv2.activities;

import br.com.fernandodutra.testeandroidv2.api.ClientAPI;
import br.com.fernandodutra.testeandroidv2.api.InterfaceAPI;
import br.com.fernandodutra.testeandroidv2.models.StatementListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 23/06/2019
 * Time: 20:42
 * TesteAndroidv2
 */
public class StatementListModel implements StatementListContract.Model {

    @Override
    public void getStatementList(final OnFinished onFinishedListener, Integer userId) {
        InterfaceAPI apiService =
                ClientAPI.getClient().create(InterfaceAPI.class);

        String url = ClientAPI.URL_BASE + "/api/statements/" + String.valueOf(userId);
        Call<StatementListResponse> call = apiService.getStatementList(url);

        call.enqueue(new Callback<StatementListResponse>() {
            @Override
            public void onResponse(Call<StatementListResponse> call, Response<StatementListResponse> response) {
                StatementListResponse statementListResponse = response.body();
                onFinishedListener.onFinished(statementListResponse);
            }

            @Override
            public void onFailure(Call<StatementListResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);

            }
        });
    }
}
