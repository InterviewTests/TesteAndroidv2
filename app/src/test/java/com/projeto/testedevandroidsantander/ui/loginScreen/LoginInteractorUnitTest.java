package com.projeto.testedevandroidsantander.ui.loginScreen;

import android.content.Context;
import android.support.annotation.NonNull;

import com.projeto.testedevandroidsantander.model.UsuarioModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
public class LoginInteractorUnitTest {
    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void fetchLoginMetaData_with_vaildInput_shouldCall_presentLoginMetaData(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresenterInputSpy;
        //When
        loginInteractor.fetchLoginMetaData(loginRequest);

        //Then
        Assert.assertTrue("When the valid input is passed to LoginInteractor " +
                        "Then presentLoginMetaData should be called",
                 loginPresenterInputSpy.presentLoginMetaDataIsCalled);
    }

    @Test
    public void fetchLoginMetaData_with_vaildInput_FutureTrip_shouldCall_Worker_getFutureTrips(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        UsuarioWorkerInputSpy usuarioWorkerInputSpy = new UsuarioWorkerInputSpy();

        //When
        loginInteractor.fetchLoginMetaData(loginRequest);

        //Then
        Assert.assertTrue("When the input is passed to LoginInteractor is FutureTrip" +
                "Then getFutureUsuarios should be called in Worker",
                usuarioWorkerInputSpy.isgetFutureUsuariosMethodCalled);
    }

    @Test
    public void fetchLoginMetaData_with_vaildInput_PastTrip_shouldCall_Worker_getPastTrips(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        UsuarioWorkerInputSpy usuarioWorkerInputSpy = new UsuarioWorkerInputSpy();

        //When
        loginInteractor.fetchLoginMetaData(loginRequest);

        //Then
        Assert.assertTrue("When the input is passed to LoginInteractor is FutureTrip" +
                        "Then getFutureUsuarios should be called in Worker",
                usuarioWorkerInputSpy.isgetPastUsuariosMethodCalled);
    }


    private class LoginPresenterInputSpy implements LoginPresenterInput {

        boolean presentLoginMetaDataIsCalled = false;
        LoginResponse loginResponseCopy;
        @Override
        public void presentLoginMetaData(LoginResponse response) {
            presentLoginMetaDataIsCalled = true;
            loginResponseCopy = response;
        }

        @Override
        public void visibleProgressBar() {

        }

        @Override
        public void hideProgressBar() {

        }

        @Override
        public void showMessageLoginError(String message) {

        }

        @Override
        public void setLoginSharedPreferences(String login) {

        }

        @Override
        public String getMessageLoginError() {
            return null;
        }

        @Override
        public String getMessageCpfError() {
            return null;
        }

        @Override
        public String getMessageSenhaError() {
            return null;
        }

        @Override
        public Context getContext() {
            return null;
        }
    }

    private class UsuarioWorkerInputSpy implements UsuarioWorkerInput {

        boolean isgetFutureUsuariosMethodCalled = false;
        boolean isgetPastUsuariosMethodCalled = false;

        @NonNull
        private ArrayList<UsuarioModel> getUsuarioModels() {
            ArrayList<UsuarioModel> usuariosList = new ArrayList<>();
            UsuarioModel usuario1 = new UsuarioModel();
            usuario1.agencia = "132";
            usuario1.conta = "4564";
            usuario1.id = new Long(1);
            usuario1.nome = "Teste";
            usuario1.saldo = 20d;
            usuariosList.add(usuario1);
            return usuariosList;
        }

        @Override
        public void getUsuarioAccount(LoginRequest loginRequest) {

        }

        @Override
        public String getUserSharedPreferences() {
            return null;
        }
    }

    private class UsuarioWorkerInputReturnNullSpy implements UsuarioWorkerInput {

        @Override
        public void getUsuarioAccount(LoginRequest loginRequest) {

        }

        @Override
        public String getUserSharedPreferences() {
            return null;
        }
    }
}
