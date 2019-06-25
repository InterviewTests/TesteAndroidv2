package br.com.fernandodutra.testeandroidv2.activities;

import android.content.Context;

import br.com.fernandodutra.testeandroidv2.api.ClientAPI;
import br.com.fernandodutra.testeandroidv2.api.InterfaceAPI;
import br.com.fernandodutra.testeandroidv2.models.StatementListResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 23/06/2019
 * Time: 22:10
 * TesteAndroidv2
 */
public class StatementListPresterTest {

    private Context context;
    private StatementListResponse statementListResponse;

    private InterfaceAPI interfaceAPI;
    private final CountDownLatch latch = new CountDownLatch(1);

    @Before
    public void setUp() throws Exception {

        interfaceAPI = ClientAPI.getClient().create(InterfaceAPI.class);
    }

    @Test
    public void getStatementList_validAcess() throws InterruptedException {

        String url = ClientAPI.URL_BASE + "/api/statements/1";
        interfaceAPI.getStatementList(url).enqueue(new Callback<StatementListResponse>() {
            @Override
            public void onResponse(Call<StatementListResponse> call, Response<StatementListResponse> response) {
                statementListResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<StatementListResponse> call, Throwable t) {
                latch.countDown();
            }
        });

        latch.await();
        Assert.assertNotNull(statementListResponse);
    }

    @Test
    public void getStatementList_invalidAcess() throws InterruptedException {
        String url = ClientAPI.URL_BASE + "/api/statements/f";
        interfaceAPI.getStatementList(url).enqueue(new Callback<StatementListResponse>() {
            @Override
            public void onResponse(Call<StatementListResponse> call, Response<StatementListResponse> response) {
                statementListResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<StatementListResponse> call, Throwable t) {
                latch.countDown();
            }
        });

        latch.await();
        boolean invalidAccess = (statementListResponse.getError().getCode() == 53);
        assertEquals(invalidAccess, true);

    }

}

