package com.avanade.testesantander2.loginScreen;


import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import com.avanade.testesantander2.UserAccount;
import com.orhanobut.hawk.Hawk;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class LoginInteractorUnitTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @NonNull
    private UserAccount getUserAccount() {
        UserAccount u = new UserAccount(1, "Teste", "070707", "0707", -17.33);
        return u;
    }

    @NonNull
    private LoginRequest getLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "teste@teste.com";
        loginRequest.password = "Teste@1";
        return loginRequest;
    }

    @NonNull
    private Context getContext() {
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
        return activity.getApplicationContext();
    }

    @NonNull
    private LoginViewModel getLoginViewodel() {
        LoginViewModel viewModel = new LoginViewModel();
        viewModel.userAccount = getUserAccount();
        return viewModel;
    }

    @NonNull
    private List<String> getErros() {
        ArrayList<String> listaDeErros = new ArrayList<>();
        listaDeErros.add("Erro 1");
        listaDeErros.add("Erro 2");
        return listaDeErros;
    }

    @Test
    public void checaUsuarioSalvo_shouldCall_presentSetUsuario() {
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresenterInputSpy;
        Context context = getContext();

        // When
        loginInteractor.checkUsuarioSalvo(context);

        //Then
        Assert.assertNotNull(loginPresenterInputSpy.loginRequestCopy.user);
        Assert.assertNotNull(loginPresenterInputSpy.loginRequestCopy.password);
    }

    @Test
    public void postLogin_withData_shouldCall_presentSetErro() {
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresenterInputSpy;

        Context context = getContext();
        LoginRequest loginRequest = getLoginRequest();

        // When
        loginInteractor.postLogin(context, loginRequest);

        //Then
        Assert.assertTrue(loginPresenterInputSpy.callSetErros);
        Assert.assertNotNull(loginPresenterInputSpy.errosCopy);
    }

    @Test
    public void postLogin_withData_shouldCall_gravarUsuario() {
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresenterInputSpy;

        Context context = getContext();
        LoginRequest loginRequest = getLoginRequest();
        Hawk.init(context).build();
        Hawk.deleteAll();

        // When
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Assert.assertEquals(0L, Hawk.count());
                loginInteractor.postLogin(context, loginRequest);

                //Then
                Assert.assertEquals(2L, Hawk.count());
                Assert.assertTrue(Hawk.contains(LoginInteractor.USUARIO));
                Assert.assertTrue(Hawk.contains(LoginInteractor.SENHA));
            }
        });
    }

    @Test
    public void postLogin_withData_shouldCall_presentResponseOK() {
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresenterInputSpy;

        Context context = getContext();
        LoginRequest loginRequest = getLoginRequest();

        // When
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                loginInteractor.postLogin(context, loginRequest);

                //Then
                Assert.assertTrue(loginPresenterInputSpy.callSetResponseOK);
                Assert.assertSame(loginPresenterInputSpy.viewModelCopy.userAccount, getUserAccount());
            }
        });
    }

    private class LoginPresenterInputSpy implements LoginPresenterInput {

        boolean callSetUser = false;
        boolean callSetResponseOK = false;
        boolean callSetErros = false;

        LoginRequest loginRequestCopy;
        LoginViewModel viewModelCopy;
        UserAccount userAccountCopy;
        List<String> errosCopy;

        @Override
        public void setUsuario(String login, String senha) {
            callSetUser = true;

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.user = login;
            loginRequest.password = senha;
            loginRequestCopy = loginRequest;
        }

        @Override
        public void setReponseOK(UserAccount userAccount) {
            callSetResponseOK = true;
            userAccountCopy = userAccount;
            viewModelCopy = getLoginViewodel();
            errosCopy = getErros();
        }

        @Override
        public void setErros(List<String> erros) {
            callSetErros = true;
            errosCopy = erros;
        }
    }


}
