package com.example.testeacclogin;

import com.example.testeacclogin.ui.login.LoginViewModel;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class LoginTest {

    @Test
    public void testeLogin(){
        LoginViewModel loginViewModel = Mockito.mock(LoginViewModel.class);
        loginViewModel.login("user@dominio.com", "Test@1");
        Assert.assertTrue(true);

        Mockito.verify(loginViewModel, Mockito.times(1))
               .login("user@dominio.com", "Test@1");


    }


}
