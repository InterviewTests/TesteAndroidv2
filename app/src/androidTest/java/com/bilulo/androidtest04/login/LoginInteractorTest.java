package com.bilulo.androidtest04.login;

import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.interactor.LoginInteractor;

import org.junit.Assert;
import org.junit.Test;

public class LoginInteractorTest {

    @Test
    public void performLogin_shouldCall_workerPerformLogin_withVALIDParameters() {
        LoginInteractor interactor = new LoginInteractor();
        LoginWorkerSpy loginWorkerSpy = new LoginWorkerSpy();
        interactor.worker = loginWorkerSpy;
        interactor.performLogin("teste@teste.com", "Teste.");
        Assert.assertTrue(loginWorkerSpy.isPerformLoginCalled);
    }

    @Test
    public void performLogin_shouldCall_presentersetData_withVALIDParameters() {
        LoginInteractor interactor = new LoginInteractor();
        LoginWorkerSpy loginWorkerSpy = new LoginWorkerSpy();
        LoginPresenterSpy loginPresenterSpy = new LoginPresenterSpy();
        interactor.worker = loginWorkerSpy;
        interactor.presenter = loginPresenterSpy;
        loginWorkerSpy.loginInteractor = interactor;
        interactor.performLogin("teste@teste.com", "Teste.");
        Assert.assertTrue(loginPresenterSpy.setDataIsCalled);
        Assert.assertTrue(loginPresenterSpy.isSuccessfulCopy);
        Assert.assertNotNull(loginPresenterSpy.loginResponseCopy);
    }

    class LoginWorkerSpy implements LoginContract.WorkerContract {
        LoginInteractor loginInteractor;
        boolean isPerformLoginCalled = false;

        @Override
        public void performLogin(String user, String password) {
            isPerformLoginCalled = true;
            if (loginInteractor != null) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setErrorModel(null);
                UserAccountModel userAccountModel = new UserAccountModel();
                userAccountModel.setAgency("13123132");
                loginResponse.setUserAccountModel(userAccountModel);
                loginInteractor.setLoginResponse(loginResponse, true);
            }
        }
    }

    class LoginPresenterSpy implements LoginContract.PresenterContract {

        boolean setDataIsCalled = false;
        LoginResponse loginResponseCopy;
        Boolean isSuccessfulCopy;
        @Override
        public void setData(LoginResponse response, boolean isSuccessful) {
            setDataIsCalled = true;
            loginResponseCopy = response;
            isSuccessfulCopy = isSuccessful;
        }
    }
}
