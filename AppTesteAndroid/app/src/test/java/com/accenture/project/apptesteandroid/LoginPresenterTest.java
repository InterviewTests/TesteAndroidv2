package com.accenture.project.apptesteandroid;

import com.accenture.project.apptesteandroid.login.LoginPresenter;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;

public class LoginPresenterTest {


    @Test
    public void testFormatAccountNumber(){

        LoginPresenter loginPresenter = new LoginPresenter();

        //Given
        String accountNumber = "011234567";

        //When
        String actualAccountNumber = loginPresenter.formatAccountNumber(accountNumber);


        //Then
        Assert.assertThat(actualAccountNumber, is("01.123456-7"));

        //actualAccountNumber must be formatted and its result should be like
        //01.123456-7(expected value)
    }

    @Test
    public void testFormatBalance(){

        LoginPresenter loginPresenter = new LoginPresenter();

        //Given
        String balance = "79.5123";

        //When
        String actualBalance = loginPresenter.formatBalance(balance);


        //Then
        Assert.assertThat(actualBalance, is("79,51"));

        //actualBalance must be formatted and its result should be like
        //79,51(expected value)
    }


    @Test
    public void testFormatBalance2(){

        LoginPresenter loginPresenter = new LoginPresenter();

        //Given
        String balance = "8050.32";

        //When
        String actualBalance = loginPresenter.formatBalance(balance);


        //Then
        Assert.assertThat(actualBalance, is("8.050,32"));

        //actualBalance must be formatted and its result should be like
        //8.050,32(expected value)
    }

}
