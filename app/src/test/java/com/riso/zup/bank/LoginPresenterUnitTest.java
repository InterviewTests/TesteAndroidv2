package com.riso.zup.bank;

import com.riso.zup.bank.helpers.Validator;
import com.riso.zup.bank.loginScreen.LoginInteractor;
import com.riso.zup.bank.loginScreen.LoginPresenter;
import com.riso.zup.bank.models.UserInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;


public class LoginPresenterUnitTest {

    LoginInteractor.Presenter presenter;

    @Mock
    private LoginInteractor.View view;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(view);
    }

    /*ErrorUser Test*/
    @Test
    public void errorInvalidUserLoginFail(){
        String user = "testUser";
        presenter.login(user, "p@SSW0RD");
        verify(view, Mockito.never()).loginOK(anyString(), (UserInfo) Mockito.any());
        Assert.assertFalse(Validator.userValidator(user));
    }

    /*ErrorPassword Test*/
    @Test
    public void errorInvalidPasswordLoginFail(){
        String password = "aaaBBBccc";
        presenter.login("example@gmail.com", password);
        verify(view, Mockito.never()).loginOK(anyString(), (UserInfo) Mockito.any());
        Assert.assertFalse(Validator.passwordValidator(password));
    }

    /*ErrorPassword Test*/
    @Test
    public void errorEmptyUserLoginFail(){
        Boolean validUser = Validator.userValidator("");
        Assert.assertFalse(validUser);
    }

    /*ErrorPassword Test*/
    @Test
    public void errorEmptyPasswordLoginFail(){
        Boolean validPassword = Validator.userValidator("");
        Assert.assertFalse(validPassword);
    }

    @Test
    public void WhenLoginIsSuccess(){
        Boolean validUser = Validator.userValidator("riso@gmail.com");
        Boolean validPassword = Validator.passwordValidator("p@SSW0RD");

        Assert.assertEquals(true, validUser);
        Assert.assertEquals(true, validPassword);
    }
}