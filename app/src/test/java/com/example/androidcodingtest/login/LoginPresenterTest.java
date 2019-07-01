package com.example.androidcodingtest.login;

import com.example.androidcodingtest.models.UserAccount;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

public class LoginPresenterTest {
    private LoginInteractor.Presenter presenter;
    @Mock
    private LoginInteractor.View view;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(view);
    }

    @Test
    public void login_InvalidUser_LoginFail(){
        String user = "user#gmail.com";
        String password = "A1!";
        presenter.login(user, password);
        verify(view, Mockito.never()).loginSuccess(anyString(), (UserAccount) Mockito.any());
    }

    @Test
    public void login_InvalidPassword_LoginFail(){
        String user = "user@gmail.com";
        String password = "asadsa";
        presenter.login(user, password);
        verify(view, Mockito.never()).loginSuccess(anyString(), (UserAccount) Mockito.any());
    }

    @Test
    public void login_ValidInformation_LoginSuccess(){
        String user = "user@gmail.com";
        String password = "Ads1@";

        LoginPresenter loginPresenter = new LoginPresenter(view);

        Boolean validUser = loginPresenter.validateUser(user);
        Boolean validPassword = loginPresenter.validatePassword(password);

        Assert.assertEquals(true, validUser);
        Assert.assertEquals(true, validPassword);
    }
}
