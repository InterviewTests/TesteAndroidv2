package br.com.fernandodutra.testeandroidv2.activities.statementList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import br.com.fernandodutra.testeandroidv2.api.ClientAPI;
import br.com.fernandodutra.testeandroidv2.api.InterfaceAPI;
import br.com.fernandodutra.testeandroidv2.models.StatementList;
import br.com.fernandodutra.testeandroidv2.models.StatementListWorker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 30/06/2019
 * Time: 01:23
 * TesteAndroidv2_CleanCode
 */
public class StatementListApiWorkerTest {

    private StatementListWorker userAccountWorker;
    private InterfaceAPI interfaceAPI;
    private final CountDownLatch latch = new CountDownLatch(1);

    @Before
    public void setUp() throws Exception {
        userAccountWorker = new StatementListWorker();
        interfaceAPI = ClientAPI.getClient().create(InterfaceAPI.class);
    }

    @Test
    public void loginApi_validAcess() throws InterruptedException {

        String url = ClientAPI.URL_BASE + "/api/statements/1";

        interfaceAPI.getStatementList(url).enqueue(new Callback<StatementListWorker>() {
            @Override
            public void onResponse(Call<StatementListWorker> call, Response<StatementListWorker> response) {
                userAccountWorker = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<StatementListWorker> call, Throwable t) {
                latch.countDown();
            }
        });

        latch.await();
        Assert.assertNotNull(userAccountWorker);
    }

    @Test
    public void loginApi_invalidAcess() throws InterruptedException {

        String url = ClientAPI.URL_BASE + "/api/statements/1";

        interfaceAPI.getStatementList(url).enqueue(new Callback<StatementListWorker>() {
            @Override
            public void onResponse(Call<StatementListWorker> call, Response<StatementListWorker> response) {
                userAccountWorker = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<StatementListWorker> call, Throwable t) {
                latch.countDown();
            }
        });

        latch.await();
        Assert.assertNotNull(userAccountWorker.getError());
    }
}