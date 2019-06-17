package com.accenture.project.apptesteandroid;

import com.accenture.project.apptesteandroid.login.LoginInteractor;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;


public class LoginInteractorTest {

    @Test
    public void testPasswordField() {

        //tests a password only with numbers

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String senha = "7878";

        //When
        boolean actualPassword = loginInteractor.validPassword(senha);


        //Then
        Assert.assertThat(actualPassword, is(false));

        //actualPassword must be false because the password must have one special
        //character, one in Upper Case and an alphanumeric
    }

    @Test
    public void testPasswordField2() {

        //tests a password only with letters

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String senha = "abcdepoop";

        //When
        boolean actualPassword = loginInteractor.validPassword(senha);

        //Then
        Assert.assertThat(actualPassword, is(false));

        //actualPassword must be false because the password must have one special
        // character, one in Upper Case and an alphanumeric
    }

    @Test
    public void testPasswordField3() {

        //tests a password only with special characters and a alphanumeric

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String senha = "#8aad";

        //When
        boolean actualPassword = loginInteractor.validPassword(senha);


         //Then
        Assert.assertThat(actualPassword, is(false));

        //actualPassword must be false because the password must have one special
        // character, one in Upper Case and an alphanumeric

    }

    @Test
    public void testPasswordField4() {

        //tests a valid password

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();

        //When
        String senha = "#E789s";
        boolean actualPassword = loginInteractor.validPassword(senha);

         //Then
        Assert.assertThat(actualPassword, is(true));

        //Assert must be true because the password should have one special
        //character, one in Upper Case and an alphanumeric"
    }

    @Test
    public void testUserField() {

        //tests an email that does not contain '@' and '.com'

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "#android.teste";

        //When
        boolean actualUser = loginInteractor.validUser(user);

        //Then
        Assert.assertThat(actualUser, is(false));

        //actualUser must be false because email should contain '@' and'.com'
    }

    @Test
    public void testUserField2() {

        //tests an input with a cpf with '-' and '.'

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "111.222.333-56";

        //When
        boolean actualUser = loginInteractor.validUser(user);

        //Then
        Assert.assertThat(actualUser, is(false));

        //actualUser must be false because it must contain only digits
    }

    @Test
    public void testUserField3() {

        //tests an input with a cpf with more than 11 digits

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "11122233344456";

        //When
        boolean actualUser = loginInteractor.validUser(user);

        //Then
        Assert.assertThat(actualUser, is(false));

        //Assert must be false because cpf must contain only 11 digits

    }

    @Test
    public void testUserField4() {

        //tests an input with a cpf with same 11 digits

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "11111111111";

        //When
        boolean actualUser = loginInteractor.validUser(user);

        //Then
        Assert.assertThat(actualUser, is(false));


        //actualUser must be false because cpf can't be a sequence of equals digits
    }

    @Test
    public void testUserField5() {
        //tests an input with a cpf with 11 digits

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "11122233344";

        //When
        boolean actualUser = loginInteractor.validUser(user);

        //Then
        Assert.assertThat(actualUser, is(true));


         //actualUser must be true because cpf should contain 11 digits and not
         //contain anyone special characters and a sequence of equals digits
    }


    @Test
    public void testUserField6() {
        //tests a valid email

        //Given
        LoginInteractor loginInteractor = new LoginInteractor();
        String user = "jose@accenture.com";

        //When
        boolean actualUser = loginInteractor.validUser(user);

        //Then
        Assert.assertThat(actualUser, is(true));
    }
}
