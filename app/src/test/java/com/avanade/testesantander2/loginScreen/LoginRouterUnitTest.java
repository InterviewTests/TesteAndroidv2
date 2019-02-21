package com.avanade.testesantander2.loginScreen;


import com.avanade.testesantander2.UserAccount;
import com.avanade.testesantander2.homeScreen.HomeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

import static org.junit.Assert.assertEquals;

/**
 * Created by mkaratadipalayam on 28/05/17.
 */

@RunWith(RobolectricTestRunner.class)
public class LoginRouterUnitTest {
    public static String TAG = LoginRouterUnitTest.class.getSimpleName();

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }


    @Test
    public void loginRouter_callHomeScreen() {
        // Given
        LoginActivity mActivity = Robolectric.setupActivity(LoginActivity.class);
        WeakReference<LoginActivity> activityWeakReference = new WeakReference<>(mActivity);
        LoginRouter loginRouter = new LoginRouter();
        loginRouter.activity = activityWeakReference;

        // When
//        Intent expectedIntent = new Intent(mActivity, HomeActivity.class);
//        Intent actual = shadowOf(mActivity).getNextStartedActivity();

        UserAccount userAccount = new UserAccount(1, "Teste", "070707", "0707", -17.33);
        loginRouter.openHomeScreen(userAccount);
        String targetActivityName = loginRouter.intent.getComponent().getClassName();


        // Then
//        assertEquals(expectedIntent.getComponent(), actual.getComponent());
        assertEquals("When the future travel date is passed to LoginRouter"
                + " Then next Intent should be HomeActivity", targetActivityName, HomeActivity.class.getName());
    }


    @Test
    public void loginRouter_passExtra_UserAccount_to_HomeActivity() {
        // Given
        LoginActivity mActivity = Robolectric.setupActivity(LoginActivity.class);
        WeakReference<LoginActivity> activityWeakReference = new WeakReference<>(mActivity);
        LoginRouter loginRouter = new LoginRouter();
        loginRouter.activity = activityWeakReference;

        // When
        UserAccount userAccount = new UserAccount(1, "Teste", "070707", "0707", -17.33);
        loginRouter.openHomeScreen(userAccount);
        UserAccount targetUserAccount = (UserAccount) loginRouter.intent.getSerializableExtra(LoginRouter.CHAVE);

        // Then
        assertEquals("When the future travel date is passed to LoginRouter"
                + " Then next Intent should be HomeActivity", targetUserAccount, userAccount);
    }
}