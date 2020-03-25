package br.com.amilton.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import br.com.amilton.LiveDataTestUtil;

import static org.junit.Assert.*;

public class LoginViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecuteRule = new InstantTaskExecutorRule();

    private LoginViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new LoginViewModel();
    }

    @Test
    public void onClickLoginFail() {
        String login = "123";
        String password = "aB1@";

        viewModel.login.set(login);
        viewModel.password.set(password);
        viewModel.onClick();

        try {
            String erroLogin = LiveDataTestUtil.getValue(viewModel.getErrorMessage());
            assertEquals(erroLogin, LoginViewModel.ERROR_LOGIN);
        } catch (InterruptedException e) {
            fail("Exception :" + e.getMessage());
        }
    }

    @Test
    public void onClickPasswordFail() {
        String login = "teste@gmail.com";
        String password = "123";

        viewModel.login.set(login);
        viewModel.password.set(password);
        viewModel.onClick();

        try {
            String errorPassword = LiveDataTestUtil.getValue(viewModel.getErrorMessage());
            assertEquals(errorPassword, LoginViewModel.ERROR_PASSWORD);
        } catch (InterruptedException e) {
            fail("Exception :" + e.getMessage());
        }
    }

    @Test
    public void onClickLoginSucess() {
        String login = "teste@gmail.com";
        String password = "aB1@";

        viewModel.login.set(login);
        viewModel.password.set(password);
        viewModel.onClick();

        try {
            Boolean sucess = LiveDataTestUtil.getValue(viewModel.loading);
            assertTrue(sucess);
        } catch (InterruptedException e) {
            fail("Exception :" + e.getMessage());
        }
    }
}