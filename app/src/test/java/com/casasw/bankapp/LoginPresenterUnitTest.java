package com.casasw.bankapp;

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
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void presentLoginMetaData_with_vaildInput_shouldCall_displayLoginData(){
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();
        //loginResponse.loginJSON = new LoginWorker().getLoginData();

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        //When
        loginPresenter.presentLoginData(loginResponse);

        //Then
        Assert.assertTrue("When the valid input is passed to LoginPresenter Then displayLoginMetaData should be called", loginActivityInputSpy.isdisplayLoginMetaDataCalled);
    }

    @Test
    public void verify_LoginPresenter(){
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();
        //LoginModel  loginModel = new LoginModel();
        /*popular login*/

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output =  new WeakReference<LoginActivityInput>(loginActivityInputSpy);
        /*popular presenter*/

        /*então contruir string esperadas e testar assert*/

    }

    @Test
    public void testpresentLoginData() {
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponseSpy loginResponseSpy = new LoginResponseSpy();
        loginPresenter.presentLoginData(loginResponseSpy);
    }
    private class LoginActivityInputSpy implements LoginActivityInput {
        boolean isdisplayLoginMetaDataCalled = false;
        LoginViewModel loginViewModelCopy;
        @Override
        public void displayLoginData(LoginViewModel loginViewModel) {
            isdisplayLoginMetaDataCalled = true;
            loginViewModelCopy = loginViewModel;
        }
    }

    private class LoginResponseSpy extends LoginResponse {
        @Override
        public String getLoginJSON() {
            return "{\n" +
                    "    \"userAccount\": {\n" +
                    "        \"userId\": 1,\n" +
                    "        \"name\": \"Jose da Silva Teste\",\n" +
                    "        \"bankAccount\": \"2050\",\n" +
                    "        \"agency\": \"012314564\",\n" +
                    "        \"balance\": 3.3445\n" +
                    "    },\n" +
                    "    \"error\": {}\n" +
                    "}";
        }
    }
}
