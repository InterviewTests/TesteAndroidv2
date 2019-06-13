package com.accenture.project.apptesteandroid;

import com.accenture.project.apptesteandroid.login.LoginInteractor;

import org.junit.Assert;
import org.junit.Test;

public class LoginInteractorTest {

    @Test
    public void testPasswordField() {

        //tests a password only with numbers

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String senha = "7878";

        //When
        boolean result = loginInteractor.validPassword(senha);


        //Then
        Assert.assertFalse("Assert must be false because the password must have one special " +
                "character, one in Upper Case and an alphanumeric", result);
    }

    @Test
    public void testPasswordField2() {

        //tests a password only with letters

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String senha = "abcdepoop";

        //When
        boolean result = loginInteractor.validPassword(senha);

        //Then
        Assert.assertFalse("Assert must be false because the password must have one special " +
                "character, one in Upper Case and an alphanumeric", result);
    }

    @Test
    public void testPasswordField3() {

        //tests a password only with special characters and a alphanumeric

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String senha = "#8aad";

        //When
        boolean result = loginInteractor.validPassword(senha);


         //Then
        Assert.assertFalse("Assert must be false because the password must have one special " +
                "character, one in Upper Case and an alphanumeric", result);
    }

    @Test
    public void testPasswordField4() {

        //tests a valid password

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();

        //When
        String senha = "#E789s";
        boolean result = loginInteractor.validPassword(senha);

         //Then
        Assert.assertTrue("Assert must be true because the password must have one special " +
                "character, one in Upper Case and an alphanumeric", result);
    }

    @Test
    public void testUserField() {

        //tests an email that does not contain '@' and '.com'

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "#android.teste";

        //When
        boolean result = loginInteractor.validUser(user);

        //Then
        Assert.assertFalse("Assert must be false because email should contain '@' and'.com'", result);
    }

    @Test
    public void testUserField2() {

        //tests an input with a cpf with '-' and '.'

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "111.222.333-56";

        //When
        boolean result = loginInteractor.validUser(user);

         //Then
        Assert.assertFalse("Assert must be false because it must contain only digits", result);
    }

    @Test
    public void testUserField3() {

        //tests an input with a cpf with more than 11 digits

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "11122233344456";

        //When
        boolean result = loginInteractor.validUser(user);

         //Then
        Assert.assertFalse("Assert must be false because cpf must contain only 11 digits",
                result);
    }

    @Test
    public void testUserField4() {

        //tests an input with a cpf with same 11 digits

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "11111111111";

       //When
        boolean result = loginInteractor.validUser(user);


        //Then
        Assert.assertFalse("Assert must be false because cpf can't be a sequence of equals digits",result);
    }

    @Test
    public void testUserField5() {
        //tests an input with a cpf with 11 digits

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "11122233344";

        //When
        boolean result = loginInteractor.validUser(user);


         //Then
        Assert.assertTrue("Assert must be true because cpf must contain 11 digits and not " +
                "contain anyone special characters and a sequence of equals digits",result);
    }


    @Test
    public void testUserField6() {
        //tests a valid email

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "jose@accenture.com";

        //When
        boolean result = loginInteractor.validUser(user);

        //Then
        Assert.assertTrue(result);
    }
}
