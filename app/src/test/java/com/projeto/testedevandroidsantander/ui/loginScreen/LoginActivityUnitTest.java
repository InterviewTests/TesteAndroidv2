package com.projeto.testedevandroidsantander.ui.loginScreen;

import com.projeto.testedevandroidsantander.model.UsuarioDTO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityUnitTest {
    @Before
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void LoginActivity_ShouldNOT_be_Null(){
        //Given
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
        //When

        // Then
        Assert.assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_fetchLoginMetaData(){
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        loginActivity.output = loginActivityOutputSpy;

        loginActivity.displayLoginMetaData(new LoginViewModel());

        Assert.assertTrue(loginActivityOutputSpy.fetchLoginMetaDataIsCalled);
    }

    @Test
    public void onCreate_Calls_fetchLoginMetaData_withCorrectData(){
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        loginActivity.output = loginActivityOutputSpy;

        //When
        loginActivity.displayLoginMetaData(new LoginViewModel());

        //Then
        Assert.assertNotNull(loginActivity);
    }



    private class LoginActivityOutputSpy implements LoginInteractorInput {

        boolean fetchLoginMetaDataIsCalled = false;
        LoginRequest loginRequestCopy;
        @Override
        public void fetchLoginMetaData(LoginRequest request) {
            fetchLoginMetaDataIsCalled = true;
            loginRequestCopy = request;
        }

        @Override
        public void presentLoginMetaData(UsuarioDTO usuarioDTO) {
            
        }

        @Override
        public void getUserSharedPreferences() {

        }
    }

}
