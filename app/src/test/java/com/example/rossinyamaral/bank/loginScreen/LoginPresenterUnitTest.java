package com.example.rossinyamaral.bank.loginScreen;

import com.example.rossinyamaral.bank.model.UserAccountModel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;


@RunWith(RobolectricTestRunner.class)
public class LoginPresenterUnitTest {
    public static String TAG = LoginPresenterUnitTest.class.getSimpleName();

    @Test
    public void presentLoginMetaData_with_validInput_shouldCall_displayLoginMetaData() {
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.userAccountModel = new UserAccountModel();
        loginResponse.userAccountModel.setName("test");
        loginResponse.userAccountModel.setBankAccount("test");
        loginResponse.userAccountModel.setAgency("test");
        loginResponse.userAccountModel.setBalance(10);

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        //When
        loginPresenter.presentLoginData(loginResponse);

        //Then
        Assert.assertTrue("When the valid input is passed to LoginPresenter " +
                "Then displayLoginMetaData should be called",
                loginActivityInputSpy.isdisplayLoginMetaDataCalled);
    }

    @Test
    public void presentLoginMetaData_with_inValidInput_shouldNotCall_displayLoginMetaData() {
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        //When
        loginPresenter.presentLoginData(null);

        //Then
        Assert.assertFalse("When the valid input is passed to LoginPresenter " +
                "Then displayLoginMetaData should NOT be called",
                loginActivityInputSpy.isdisplayLoginMetaDataCalled);
    }

    private class LoginActivityInputSpy implements LoginActivityInput {
        boolean isdisplayLoginMetaDataCalled = false;
        UserAccountModel loginViewModelCopy;
        @Override
        public void displayLoginData(UserAccountModel loginViewModel) {
            isdisplayLoginMetaDataCalled = true;
            loginViewModelCopy = loginViewModel;
        }

        @Override
        public void displayLoginError(String message) {
            //TODO
        }
    }
}
