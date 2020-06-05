package com.bank.testeandroidv2.loginScreen;


import android.content.Intent;

import com.bank.testeandroidv2.statementScreen.StatementActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

@RunWith(RobolectricTestRunner.class)
public class LoginRouterUnitTest {
    public static String TAG = LoginRouterUnitTest.class.getSimpleName();

    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void loginRouter_passDataToNextScene() {
        //Given
        LoginRouter loginRouter = new LoginRouter();
        UserAccount uc = new UserAccount();
        uc.userId = 1L;
        uc.name = "Jose da Silva Teste";
        uc.agency = "012314564";
        uc.bankAccount = "2050";
        uc.balance = 3.3445;

        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
//        loginActivity. listOfVMFlights = flightList;
        loginActivity.router = loginRouter;
        loginRouter.activity = new WeakReference<LoginActivity>(loginActivity);

        loginRouter.passDataToNextScene(uc);

        //When - Futrure Trip is Input

        Intent intent = loginRouter.navigateToSomeWhere(0);

        //Then
        String targetActivityName = intent.getComponent().getClassName();
        Assert.assertEquals("When the future travel date is passed to LoginRouter"
                +" Then next Intent should be BoardingActivity",targetActivityName, StatementActivity.class.getName());
    }
}
