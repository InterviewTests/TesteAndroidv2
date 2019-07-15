package br.com.fernandodutra.testeandroidv2.activities.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.models.Error;
import br.com.fernandodutra.testeandroidv2.models.ErrorMessage;
import br.com.fernandodutra.testeandroidv2.models.Login;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.models.UserAccountWorker;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 16:14
 * TesteAndroidv2_CleanCode
 */
@RunWith(RobolectricTestRunner.class)
public class LoginInteractorTest {

    public List<ErrorMessage> errorMessage;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void login_invalidCPF() {

        LoginRequest loginRequest = new LoginRequest(new Login("367.464.058-94", "@Fer123"));
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);

        loginActivity.loginInteractorInput.fetchLoginMetaData(loginActivity, loginRequest);

        if (loginActivity.viewModel.errorMessage.size() > 0) {
            StringBuilder listMessage = new StringBuilder();
            this.errorMessage = loginActivity.viewModel.errorMessage;

            for (ErrorMessage em : this.errorMessage) {
                int codeError = em.getMessageId();
                assertEquals(R.string.username_required, codeError);
            }
        }
    }

    @Test
    public void login_invalidEmail() {

        LoginRequest loginRequest = new LoginRequest(new Login("fernando#.com", "@Fer123"));
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);

        loginActivity.loginInteractorInput.fetchLoginMetaData(loginActivity, loginRequest);

        if (loginActivity.viewModel.errorMessage.size() > 0) {
            StringBuilder listMessage = new StringBuilder();
            this.errorMessage = loginActivity.viewModel.errorMessage;

            for (ErrorMessage em : this.errorMessage) {
                int codeError = em.getMessageId();
                assertEquals(R.string.username_required, codeError);
            }
        }
    }

    @Test
    public void login_invalidPassword() {

        LoginRequest loginRequest = new LoginRequest(new Login("fernando@gmail.com", "123456"));
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);

        loginActivity.loginInteractorInput.fetchLoginMetaData(loginActivity, loginRequest);

        if (loginActivity.viewModel.errorMessage.size() > 0) {
            StringBuilder listMessage = new StringBuilder();
            this.errorMessage = loginActivity.viewModel.errorMessage;

            for (ErrorMessage em : this.errorMessage) {
                int codeError = em.getMessageId();
                assertEquals(R.string.password_required, codeError);
            }
        }
    }

    @Test
    public void login_validCPF() {

        LoginRequest loginRequest = new LoginRequest(new Login("367.464.058-93", "@Fer123"));
        LoginInteractor loginInteractor = new LoginInteractor();

        loginInteractor.loginApiWorkerInput = loginInteractor.getLoginApiInput();
        loginInteractor.loginResponse = new LoginResponse();

        boolean validCPF = loginInteractor.login(loginRequest);

        assertTrue(validCPF);
    }

    @Test
    public void login_validEmail() {

        LoginRequest loginRequest = new LoginRequest(new Login("fernando@gmail.com", "@Fer123"));
        LoginInteractor loginInteractor = new LoginInteractor();

        loginInteractor.loginApiWorkerInput = loginInteractor.getLoginApiInput();
        loginInteractor.loginResponse = new LoginResponse();

        boolean validCPF = loginInteractor.login(loginRequest);

        assertTrue(validCPF);
    }

    @Test
    public void login_validPassword() {

        LoginRequest loginRequest = new LoginRequest(new Login("367.464.058-93", "@Fer123"));
        LoginInteractor loginInteractor = new LoginInteractor();

        loginInteractor.loginApiWorkerInput = loginInteractor.getLoginApiInput();
        loginInteractor.loginResponse = new LoginResponse();

        boolean validCPF = loginInteractor.login(loginRequest);

        assertTrue(validCPF);
    }

    @Test
    public void login_invalid_Email_CPF_Password() {

        LoginRequest loginRequest = new LoginRequest(new Login("", ""));
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);

        loginActivity.loginInteractorInput.fetchLoginMetaData(loginActivity, loginRequest);

        assertTrue(loginActivity.viewModel.errorMessage.size() > 0);
    }

    @Test
    public void validLoginApi_valid() {

        UserAccountWorker userAccountWorker = new UserAccountWorker();
        userAccountWorker.setUserAccount(new UserAccount(1, "Fernando", "1234", "65432-1", 1000.00d));

        LoginInteractor loginInteractor = new LoginInteractor();
        loginInteractor.loginResponse = new LoginResponse();
        loginInteractor.validLoginApi(userAccountWorker);

        assertEquals(0, loginInteractor.loginResponse.errorMessage.size());
    }

    @Test
    public void validLoginApi_invalid() {

        UserAccountWorker userAccountWorker = new UserAccountWorker();
        userAccountWorker.setUserAccount(new UserAccount());
        userAccountWorker.setError(new Error(53,"Error Message"));

        LoginInteractor loginInteractor = new LoginInteractor();
        loginInteractor.loginResponse = new LoginResponse();
        loginInteractor.validLoginApi(userAccountWorker);

        int invalid = loginInteractor.loginResponse.errorMessage.get(0).getMessageId();

        assertEquals(R.string.login_notfound_code_53, invalid);
    }

}