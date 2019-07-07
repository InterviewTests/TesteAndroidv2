package com.projeto.testedevandroidsantander.ui.loginScreen;

import android.content.Context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

@RunWith(RobolectricTestRunner.class)
public class LoginPresenterUnitTest {
    public static String TAG = LoginPresenterUnitTest.class.getSimpleName();

    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void presentLoginMetaData_with_vaildInput_shouldCall_displayLoginMetaData(){
        //Given
        LoginPresenter homePresenter = new LoginPresenter();
        LoginResponse homeResponse = new LoginResponse();

        LoginActivityInputSpy homeActivityInputSpy = new LoginActivityInputSpy();
        homePresenter.output = new WeakReference<LoginActivityInput>(homeActivityInputSpy);

        //When
        homePresenter.presentLoginMetaData(homeResponse);

        //Then
        Assert.assertTrue("When the valid input is passed to LoginPresenter Then displayLoginMetaData should be called", homeActivityInputSpy.isdisplayLoginMetaDataCalled);
    }

    @Test
    public void presentLoginMetaData_with_inVaildInput_shouldNotCall_displayLoginMetaData(){
        //Given
        LoginPresenter homePresenter = new LoginPresenter();
        LoginResponse homeResponse = new LoginResponse();

        LoginActivityInputSpy homeActivityInputSpy = new LoginActivityInputSpy();
        homePresenter.output = new WeakReference<LoginActivityInput>(homeActivityInputSpy);

        //When
        homePresenter.presentLoginMetaData(homeResponse);

        //Then
        Assert.assertFalse("When the valid input is passed to LoginPresenter Then displayLoginMetaData should NOT be called", homeActivityInputSpy.isdisplayLoginMetaDataCalled);
    }


    private class LoginActivityInputSpy implements LoginActivityInput {
        public boolean isdisplayLoginMetaDataCalled = false;
        public LoginViewModel homeViewModelCopy;
        @Override
        public void displayLoginMetaData(LoginViewModel homeViewModel) {
            isdisplayLoginMetaDataCalled = true;
            homeViewModelCopy = homeViewModel;
        }

        @Override
        public void showProgressBar() {

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
}
