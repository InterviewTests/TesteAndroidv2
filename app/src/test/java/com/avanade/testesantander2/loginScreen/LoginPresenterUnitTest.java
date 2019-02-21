package com.avanade.testesantander2.loginScreen;

import androidx.annotation.NonNull;

import com.avanade.testesantander2.UserAccount;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkaratadipalayam on 11/10/16.
 */
@RunWith(RobolectricTestRunner.class)
public class LoginPresenterUnitTest {
    public static String TAG = LoginPresenterUnitTest.class.getSimpleName();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }


    @NonNull
    private LoginRequest getloginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "teste@teste.com";
        loginRequest.password = "Teste@01";
        return loginRequest;
    }

    @NonNull
    private List<String> getErros() {
        ArrayList<String> listaDeErros = new ArrayList<>();
        listaDeErros.add("Erro 1");
        listaDeErros.add("Erro 2");
        return listaDeErros;
    }

    @NonNull
    private LoginViewModel getLoginViewModel() {
        LoginViewModel loginViewModel = new LoginViewModel();
        UserAccount u = new UserAccount(1, "Teste", "070707", "0707", -17.33);
        loginViewModel.userAccount = u;
        return loginViewModel;
    }

    @Test
    public void apresentarUsuario_activitySetUsuario() {
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        //When
        loginPresenter.setUsuario(getloginRequest().user, getloginRequest().password);

        //Then
        Assert.assertTrue(loginActivityInputSpy.callSetUsuario);
        Assert.assertSame(loginActivityInputSpy.loginRequestCopy.user, getloginRequest().user);
        Assert.assertSame(loginActivityInputSpy.loginRequestCopy.password, getloginRequest().password);

    }

    @Test
    public void apresentarErros_activityShowErros() {
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        List<String> listaDeErros = getErros();

        //When
        loginPresenter.setErros(listaDeErros);

        //Then
        Assert.assertTrue(loginActivityInputSpy.callShowErros);
        Assert.assertSame(listaDeErros, loginActivityInputSpy.errosCopy);
    }

    @Test
    public void apresentarUserAccount_activityOpenHomeScreen() {
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        UserAccount userAccount = getLoginViewModel().userAccount;

        //When
        loginPresenter.setReponseOK(userAccount);

        //Then
        Assert.assertTrue(loginActivityInputSpy.callSetResponseOK);
        Assert.assertSame(userAccount, loginActivityInputSpy.loginViewModelCopy.userAccount);
    }

    private class LoginActivityInputSpy implements LoginActivityInput {

        boolean callSetUsuario = false;
        boolean callSetResponseOK = false;
        boolean callShowErros = false;

        LoginRequest loginRequestCopy;
        List<String> errosCopy;
        LoginViewModel loginViewModelCopy;


        @Override
        public void showErros(List<String> erros) {
            callShowErros = true;
            errosCopy = erros;
        }

        @Override
        public void openHomeScreen(UserAccount userAccount) {

            callSetResponseOK = true;
            LoginViewModel loginViewModel = new LoginViewModel();
            loginViewModel.userAccount = userAccount;
            loginViewModelCopy = loginViewModel;
        }

        @Override
        public void setUsuario(String user, String password) {
            callSetUsuario = true;

            loginRequestCopy = new LoginRequest();
            loginRequestCopy.user = user;
            loginRequestCopy.password = password;
        }
    }
}
