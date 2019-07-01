package br.com.fernandodutra.testeandroidv2.activities.login;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

import br.com.fernandodutra.testeandroidv2.models.StatementList;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.models.UserAccountWorker;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 17:30
 * TesteAndroidv2_CleanCode
 */
@RunWith(RobolectricTestRunner.class)
public class LoginPresenterTest {

    private UserAccountWorker userAccountWorkerIsEmpty;
    private UserAccountWorker userAccountWorkerIsNotEmpty;

    @Before
    public void setUp() throws Exception {
        this.userAccountWorkerIsEmpty = null;
        this.userAccountWorkerIsNotEmpty = new UserAccountWorker();
        this.userAccountWorkerIsNotEmpty.setUserAccount(new UserAccount(1, "Fernando", "1234", "65432-1", 1000.00d));
    }

    @Test
    public void presentLoginMetaData_UserAccountIsNotNull() {
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.userAccountWorker = userAccountWorkerIsNotEmpty;

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.loginActivityInputWeakReference = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        //When
        loginPresenter.presentLoginMetaData(loginResponse);

        //Then
        Assert.assertNotNull(loginActivityInputSpy.loginViewModelCopy.userAccountWorker);
    }

    @Test
    public void presentLoginMetaData_UserAccountIsNull() {
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.userAccountWorker = userAccountWorkerIsEmpty;

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.loginActivityInputWeakReference = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        //When
        loginPresenter.presentLoginMetaData(loginResponse);

        //Then
        Assert.assertNull(loginActivityInputSpy.loginViewModelCopy.userAccountWorker);
    }

    private class LoginActivityInputSpy implements LoginActivityInput {
        public boolean isdisplayLoginMetaDataCalled = false;
        public LoginViewModel loginViewModelCopy;

        @Override
        public void displayLoginMetaData(LoginViewModel loginViewModel) {
            loginViewModelCopy = loginViewModel;
        }
    }

}