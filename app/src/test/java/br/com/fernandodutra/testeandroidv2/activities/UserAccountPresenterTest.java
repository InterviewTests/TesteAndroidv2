package br.com.fernandodutra.testeandroidv2.activities;

import android.content.Context;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.api.ClientAPI;
import br.com.fernandodutra.testeandroidv2.api.InterfaceAPI;
import br.com.fernandodutra.testeandroidv2.models.Login;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.models.UserAccountResponse;
import br.com.fernandodutra.testeandroidv2.models.Error;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 23/06/2019
 * Time: 17:24
 * TesteAndroidv2
 */
public class UserAccountPresenterTest {

    private Context context;
    private UserAccountPresenter userAccountPresenter;
    private UserAccountActivityTest userAccountActivityTest;
    private UserAccountResponse userAccountResponse;
    private InterfaceAPI interfaceAPI;
    private final CountDownLatch latch = new CountDownLatch(1);


    @Before
    public void setUp() throws Exception {
        userAccountActivityTest = new UserAccountActivityTest();

        userAccountPresenter = new UserAccountPresenter(
                userAccountActivityTest,
                new UserAccountModel(context)
        );

        interfaceAPI = ClientAPI.getClient().create(InterfaceAPI.class);
    }

    @Test
    public void login_invalidCPF() {
        Login login = new Login("367.464.058-94", "@Fer123");
        userAccountPresenter.login(login);
        assertEquals(R.string.username_required, userAccountActivityTest.responseIntView);
    }

    @Test
    public void login_invalidEmail() {
        Login login = new Login("fernando#.com", "@Fer123");
        userAccountPresenter.login(login);
        assertEquals(R.string.username_required, userAccountActivityTest.responseIntView);
    }

    @Test
    public void login_invalidPassword() {
        Login login = new Login("fernando#.com", "123456");
        userAccountPresenter.login(login);
        assertEquals(R.string.password_required, userAccountActivityTest.responseIntView);
    }

    @Test
    public void login_validCPF() {
        Login login = new Login("367.464.058-93", "@Fer123");
        userAccountPresenter.login(login);
        assertEquals(-1, userAccountActivityTest.responseIntView);
    }

    @Test
    public void login_validEmail() {
        Login login = new Login("fernando@hotmail.com", "@Fer123");
        userAccountPresenter.login(login);
        assertEquals(-1, userAccountActivityTest.responseIntView);
    }

    @Test
    public void login_validPassword() {
        Login login = new Login("fernando@hotmail.com", "@Fer1234");
        userAccountPresenter.login(login);
        assertEquals(-1, userAccountActivityTest.responseIntView);
    }

    @Test
    public void loginApi_validAcess() throws InterruptedException {
        Login login = new Login("fernando@hotmail.com", "@Fer123");

        interfaceAPI.getUserAccount(login).enqueue(new Callback<UserAccountResponse>() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {
                userAccountResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {
                latch.countDown();
            }
        });

        latch.await();
        Assert.assertNotNull(userAccountResponse);
    }

    @Test
    public void loginApi_invalidAcess() throws InterruptedException {
        Login login = new Login("", "");

        interfaceAPI.getUserAccount(login).enqueue(new Callback<UserAccountResponse>() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {
                userAccountResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {
                latch.countDown();
            }
        });

        latch.await();

        boolean invalidAccess = (userAccountResponse.getError().getCode() == 53);

        assertEquals(invalidAccess, true);
    }

    @Test
    public void validLoginApi_valid() {
        UserAccount userAccount = new UserAccount();
        UserAccountResponse userAccountResponse = new UserAccountResponse();
        userAccountResponse.setUserAccount(userAccount);
        boolean validLoginApi = userAccountPresenter.validLoginApi(userAccountResponse);
        assertEquals(validLoginApi, true);
    }

    @Test
    public void validLoginApi_invalid() {
        UserAccountResponse userAccountResponse = new UserAccountResponse();
        userAccountResponse.setError(new Error(53, "Erro de Acesso"));
        boolean validLoginApi = userAccountPresenter.validLoginApi(userAccountResponse);
        assertEquals(validLoginApi, false);
    }
}