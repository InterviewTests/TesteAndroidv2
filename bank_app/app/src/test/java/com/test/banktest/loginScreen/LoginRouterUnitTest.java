package com.test.banktest.loginScreen;

import android.content.Intent;

import com.test.banktest.model.UserModel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

@RunWith(RobolectricTestRunner.class)
public class LoginRouterUnitTest {

    @Test
    public void verify_passUser_nextScene(){

        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
        LoginRouter loginRouter = new LoginRouter();
        loginRouter.activity = new WeakReference<>(activity);

        Intent intent = loginRouter.navigateToSomeWhere(0);

        loginRouter.passUserToNextScene(new UserModel(),intent);

        Assert.assertTrue(intent.hasExtra("user"));
    }
}
