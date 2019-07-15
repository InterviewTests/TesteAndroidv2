package br.com.fernandodutra.testeandroidv2.activities.statementList;

import br.com.fernandodutra.testeandroidv2.api.ClientAPI;
import br.com.fernandodutra.testeandroidv2.api.InterfaceAPI;
import br.com.fernandodutra.testeandroidv2.models.StatementListWorker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 20:02
 * TesteAndroidv2_CleanCode
 */
interface StatementListApiWorkerInput {
    void getUserAccountResponse(final OnFinished onFinishedListener, Integer userId);
}

public class StatementListApiWorker implements StatementListApiWorkerInput {

    @Override
    public void getUserAccountResponse(final OnFinished onFinishedListener, Integer userId) {
        InterfaceAPI apiService =
                ClientAPI.getClient().create(InterfaceAPI.class);

        String url = ClientAPI.URL_BASE + "/api/statements/" + String.valueOf(userId);

        Call<StatementListWorker> call = apiService.getStatementList(url);
        call.enqueue(new Callback<StatementListWorker>() {
            @Override
            public void onResponse(Call<StatementListWorker> call, Response<StatementListWorker> response) {
                StatementListWorker statementListWorker = response.body();
                onFinishedListener.onFinished(statementListWorker);
            }

            @Override
            public void onFailure(Call<StatementListWorker> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
