package br.com.fernandodutra.testeandroidv2.activities.login;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import br.com.fernandodutra.testeandroidv2.api.ClientAPI;
import br.com.fernandodutra.testeandroidv2.api.InterfaceAPI;
import br.com.fernandodutra.testeandroidv2.models.Login;
import br.com.fernandodutra.testeandroidv2.models.UserAccountWorker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 17:35
 * TesteAndroidv2_CleanCode
 */
public class LoginApiWorkerTest {

    private UserAccountWorker userAccountWorker;
    private InterfaceAPI interfaceAPI;
    private final CountDownLatch latch = new CountDownLatch(1);

    @Before
    public void setUp() throws Exception {
        userAccountWorker = new UserAccountWorker();
        interfaceAPI = ClientAPI.getClient().create(InterfaceAPI.class);
    }

    @Test
    public void loginApi_validAcess() throws InterruptedException {
        LoginRequest loginRequest = new LoginRequest(new Login("fernando@hotmail.com", "@Fer123"));

        interfaceAPI.getUserAccount(loginRequest.getLogin()).enqueue(new Callback<UserAccountWorker>() {
            @Override
            public void onResponse(Call<UserAccountWorker> call, Response<UserAccountWorker> response) {
                userAccountWorker = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<UserAccountWorker> call, Throwable t) {
                latch.countDown();
            }
        });

        latch.await();
        Assert.assertNotNull(userAccountWorker);
    }

    @Test
    public void loginApi_invalidAcess() throws InterruptedException {
        LoginRequest loginRequest = new LoginRequest(new Login());

        interfaceAPI.getUserAccount(loginRequest.getLogin()).enqueue(new Callback<UserAccountWorker>() {
            @Override
            public void onResponse(Call<UserAccountWorker> call, Response<UserAccountWorker> response) {
                userAccountWorker = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<UserAccountWorker> call, Throwable t) {
                latch.countDown();
            }
        });

        latch.await();
        Assert.assertNotNull(userAccountWorker.getError());
    }
}