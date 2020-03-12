package com.test.banktest.loginScreen;

import com.test.banktest.homeScreen.HomeRequest;
import com.test.banktest.homeScreen.HomeResponse;
import com.test.banktest.worker.serviceWorker.Listener;
import com.test.banktest.worker.serviceWorker.ServiceWorkerInput;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginInteractorUnitTest {

    @Test
    public void login_with_validInput_shouldCall_Worker(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "a@a.com";
        loginRequest.password = "7@A";

        //Setup TestDoubles
        loginInteractor.output = new LoginPresenterInputSpy();
        ServiceWorkerInputSpy serviceWorkerInputSpy = new ServiceWorkerInputSpy();
        loginInteractor.setServiceWorker(serviceWorkerInputSpy);

        //When
        loginInteractor.login(loginRequest);

        //Then
        Assert.assertTrue("When the user/password passed is correct to LoginInteractor" +
                        "then serviceWorker should be called",
                serviceWorkerInputSpy.loginIsCalled);
    }

    @Test
    public void login_with_validInput_shouldNOTCall_Worker_and_ShouldCallPresenter(){
        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "bla";
        loginRequest.password = "bla";

        //Setup TestDoubles
        LoginPresenterInputSpy loginPresentInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresentInputSpy;
        ServiceWorkerInputSpy serviceWorkerInputSpy = new ServiceWorkerInputSpy();
        loginInteractor.setServiceWorker(serviceWorkerInputSpy);

        //When
        loginInteractor.login(loginRequest);

        //Then
        Assert.assertTrue("When the user/password passed is invalid to LoginInteractor" +
                        "then serviceWorker should NOT be called and Presenter should be called",
                !serviceWorkerInputSpy.loginIsCalled && loginPresentInputSpy.presentIsCalled);
    }

    private class LoginPresenterInputSpy implements LoginPresenterInput {

        boolean presentIsCalled;

        @Override
        public void presentLoginData(LoginResponse response) {
                presentIsCalled = true;
        }

        @Override
        public void presentLastUser(LoginResponse response) {

        }
    }

    private class ServiceWorkerInputSpy implements ServiceWorkerInput {

        boolean loginIsCalled;

        @Override
        public void login(LoginRequest loginRequest, Listener<LoginResponse> listener) {
            loginIsCalled = true;
        }

        @Override
        public void getStatements(HomeRequest request, Listener<HomeResponse> homeResponseListener) {
        }
    }
}
