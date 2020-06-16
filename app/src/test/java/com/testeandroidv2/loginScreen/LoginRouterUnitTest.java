package com.testeandroidv2.loginScreen;

import android.content.Intent;
import android.os.Build;

import com.testeandroidv2.statementScreen.StatementActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.ref.WeakReference;
import java.util.Objects;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 19)
public class LoginRouterUnitTest {

    @Test
    public void loginRouter_determineNextScreen_when_Authetication_is_valid(){
        // Given
        LoginRouter loginRouter = new LoginRouter();
        LoginModel loginModel = new LoginModel(1, "Teste", 1234, "56789", 3.3332);

        // Setup Double Test
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        activity.login = loginModel;
        activity.router = loginRouter;
        loginRouter.activity = new WeakReference<>(activity);

        // When
        Intent intent = loginRouter.navigateToSomeWhere(0);

        // Then
        String targetActivityName = Objects.requireNonNull(intent.getComponent()).getClassName();
        Assert.assertEquals("When the login authetication is valided then next Intent should be statementActivity", targetActivityName, StatementActivity.class.getName());
    }
}
