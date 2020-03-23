package com.bankapp.loginScreen;


import android.content.Intent;
import android.os.Build;

import com.bankapp.statementScreen.StatementActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.ref.WeakReference;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.KITKAT)
public class LoginRouterUnitTest {

    LoginRouter loginRouter;
    UserAccount userAccount;
    LoginActivity loginActivity;

    @Before
    public void setUp(){
        loginRouter = new LoginRouter();
        userAccount = new UserAccount();

        loginActivity = Robolectric.setupActivity(LoginActivity.class);
        loginActivity.userAccount = userAccount;
        loginActivity.router = loginRouter;
        loginRouter.activity = new WeakReference<LoginActivity>(loginActivity);
    }

    @Test
    public void loginRouter_determineNextScreen_Statement() {
        Intent intent = loginRouter.determineNextScreen();
        String targetActivityName = intent.getComponent().getClassName();
        assertEquals(targetActivityName, StatementActivity.class.getName());
    }

    @Test
    public void determineNextScreen_ShouldNOT_be_Null() {
        Intent intent = loginRouter.determineNextScreen();
        assertNotNull(intent);
    }
}
