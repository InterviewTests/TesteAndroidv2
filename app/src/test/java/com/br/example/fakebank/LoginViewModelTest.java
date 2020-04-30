package com.br.example.fakebank;

import com.br.example.fakebank.domains.services.LoginService;
import com.br.example.fakebank.presentations.handles.LoginHandle;
import com.br.example.fakebank.presentations.viewModels.LoginViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

@RunWith(JUnit4.class)
public class LoginViewModelTest {

    @Mock
    private LoginService loginService;

    @Mock
    private LoginHandle loginHandle;

    private LoginViewModel loginViewModel;

    @Before
    public void init(){
        loginViewModel = new LoginViewModel(loginService, loginHandle);
    }

    @Test
    public void isValidateUserName(){
        boolean isValidResult = loginViewModel.isUserValid("Ricardo");
        Assert.assertFalse(isValidResult);
    }

    @Test
    public void isValidateUserPassword(){
        boolean isValidResult = loginViewModel.isPasswordValid("Test@1");
        Assert.assertTrue(isValidResult);
    }

}
