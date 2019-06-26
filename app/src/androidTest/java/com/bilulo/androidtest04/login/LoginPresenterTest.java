package com.bilulo.androidtest04.login;

import com.bilulo.androidtest04.data.model.ErrorModel;
import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.presenter.LoginPresenter;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;

public class LoginPresenterTest {

    @Test
    public void setData_shouldCall_activityLoginSuccessful_withVALIDResponse_AND_SUCCESSFULRequest() {
        LoginPresenter presenter = new LoginPresenter();
        LoginActivitySpy loginActivitySpy = new LoginActivitySpy();
        presenter.activity = new WeakReference<LoginContract.ActivityContract>(loginActivitySpy);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setErrorModel(null);
        UserAccountModel userAccountModel = new UserAccountModel();
        userAccountModel.setAgency("012314588");
        userAccountModel.setBalance(BigDecimal.valueOf(3123.33455));
        userAccountModel.setName("Pedro Alvares Cabral");
        userAccountModel.setUserId(2);
        userAccountModel.setBankAccount("3333");
        loginResponse.setUserAccountModel(userAccountModel);
        presenter.setData(loginResponse, true);
        Assert.assertTrue(loginActivitySpy.isLoginSuccessfulCalled);
    }

    @Test
    public void setData_shouldCall_activityDisplayError_withVALIDResponse_AND_UNSUCCESSFULRequest() {
        LoginPresenter presenter = new LoginPresenter();
        LoginActivitySpy loginActivitySpy = new LoginActivitySpy();
        presenter.activity = new WeakReference<LoginContract.ActivityContract>(loginActivitySpy);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setErrorModel(null);
        UserAccountModel userAccountModel = new UserAccountModel();
        userAccountModel.setAgency("012314588");
        userAccountModel.setBalance(BigDecimal.valueOf(3123.33455));
        userAccountModel.setName("Pedro Alvares Cabral");
        userAccountModel.setUserId(2);
        userAccountModel.setBankAccount("3333");
        loginResponse.setUserAccountModel(userAccountModel);
        presenter.setData(loginResponse, false);
        Assert.assertFalse(loginActivitySpy.isLoginSuccessfulCalled);
        Assert.assertTrue(loginActivitySpy.isDisplayErrorCalled);
    }

    @Test
    public void setData_shouldCall_activityDisplayError_withNULLResponse_AND_SUCCESSFULRequest() {
        LoginPresenter presenter = new LoginPresenter();
        LoginActivitySpy loginActivitySpy = new LoginActivitySpy();
        presenter.activity = new WeakReference<LoginContract.ActivityContract>(loginActivitySpy);
        presenter.setData(null, true);
        Assert.assertFalse(loginActivitySpy.isLoginSuccessfulCalled);
        Assert.assertTrue(loginActivitySpy.isDisplayErrorCalled);
    }

    @Test
    public void setData_shouldCall_activityDisplayError_withINVALIDResponse_AND_SUCCESSFULRequest() {
        LoginPresenter presenter = new LoginPresenter();
        LoginActivitySpy loginActivitySpy = new LoginActivitySpy();
        LoginResponse loginResponse = new LoginResponse();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(1);
        errorModel.setMessage("Error");
        loginResponse.setErrorModel(errorModel);
        presenter.activity = new WeakReference<LoginContract.ActivityContract>(loginActivitySpy);
        presenter.setData(loginResponse, true);
        Assert.assertFalse(loginActivitySpy.isLoginSuccessfulCalled);
        Assert.assertTrue(loginActivitySpy.isDisplayErrorCalled);
    }

    class LoginActivitySpy implements LoginContract.ActivityContract {
        boolean isLoginSuccessfulCalled = false;
        boolean isDisplayErrorCalled = false;

        @Override
        public void loginSuccessful(UserAccountModel userAccountModel) {
            isLoginSuccessfulCalled = true;
        }

        @Override
        public void displayError() {
            isDisplayErrorCalled = true;
        }
    }
}
