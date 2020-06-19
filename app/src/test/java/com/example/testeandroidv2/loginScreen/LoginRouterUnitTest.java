package com.example.testeandroidv2.loginScreen;

import android.content.Intent;

import com.example.testeandroidv2.statementScreen.StatementActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.ref.WeakReference;
import java.util.Objects;

@RunWith(RobolectricTestRunner.class)
@Config(sdk=19)
public class LoginRouterUnitTest {

    @Test
    public void loginRouter_determineNextScreen_when_Authentication_valid(){
        // Given
        LoginRouter loginRouter = new LoginRouter();
        LoginModel loginModel = new LoginModel("111.111.111-11", "A@s");

        // Setup Double Test
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().resume().get();
        activity.login = loginModel;
        activity.router = loginRouter;
        loginRouter.activity = new WeakReference<>(activity);

        // When
        Intent intent = loginRouter.navigateToSomeWhere(0);

        // Then
        String targetActivityName = Objects.requireNonNull(intent.getComponent()).getClassName();
        Assert.assertEquals("When the login authentication is valided then next Intent should be statementActivity", targetActivityName, StatementActivity.class.getName());
    }
}
