package com.example.rossinyamaral.bank.loginScreen;

import android.content.ComponentName;
import android.content.Intent;

import com.example.rossinyamaral.bank.model.UserAccountModel;
import com.example.rossinyamaral.bank.statementsScreen.StatementsActivity;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginRouterUnitTest {

    @Test
    public void determineWhereToNavigate() {
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);

        ComponentName component = loginActivity.router.navigateToSomeWhere().getComponent();
        Assert.assertNotNull(component);
        Assert.assertEquals(StatementsActivity.class.getName(), component.getClassName());
    }

    @Test
    public void checkDataToNextScene() {
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);

        Intent intent = loginActivity.router.navigateToSomeWhere();
        loginActivity.router.passDataToNextScene(intent, new UserAccountModel());
        Assert.assertTrue(intent.hasExtra("userAccount"));
    }


}
