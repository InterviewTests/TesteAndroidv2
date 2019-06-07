package com.example.testeacclogin.ui.login;

import com.example.testeacclogin.data.LoginRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginViewModelTest extends TestCase {


    @Mock
    private LoginViewModel model;
    LoginRepository loginRepository;


    @Before
    public void setUp() {
        model = new LoginViewModel(loginRepository);

    }

    @Test
    public void IsUserNameValid()    {

        String username = null;
        boolean user = model.isUserNameValid(username);
        assertFalse(user);
    }

    @Test(expected = NullPointerException.class)
    public void IsUserNameValidEmail(){

        String username = "user@dominio.com";
        boolean em = model.isUserNameValid(username);
        assertNull(em);
    }

    @Test
    public void IsUserNamevalidCPF(){

        String username = "12345678901";

        boolean cpf = model.isUserNameValid(username);
        boolean expCPF = true;
        assertThat(expCPF, is(equalTo(cpf)));

    }

    @Test
    public void isPasswordTest(){

        String password = "Ale@1";

      model = new LoginViewModel(loginRepository);

      boolean expPass = true;
      boolean pass = model.isPasswordValid(password);
      assertThat(expPass, is(equalTo(pass)));

    }
}
