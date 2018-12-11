package com.example.rossinyamaral.bank.statementsScreen;

import android.content.Intent;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class StatementsRouterUnitTest {

    @Test
    public void determineWhereToNavigate() {
        StatementsActivity activity = Robolectric.setupActivity(StatementsActivity.class);

        Intent intent = activity.router.navigateToSomeWhere(0);
        Assert.assertNotNull(intent);
    }

    @Test
    public void checkDataToNextScene() {
        StatementsActivity loginActivity = Robolectric.setupActivity(StatementsActivity.class);

        Intent intent = loginActivity.router.navigateToSomeWhere(0);
        loginActivity.router.passDataToNextScene(0, intent);
        Assert.assertNotNull(intent);
        Assert.assertNull(intent.getExtras());
    }
}
