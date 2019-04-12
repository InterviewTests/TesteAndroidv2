package com.santander.vicolmoraes.santander;

import com.santander.vicolmoraes.santander.View.CurrencyActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class CurrencyTest {

    private CurrencyActivity currencyActivity;
    private ActivityController<CurrencyActivity> activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(CurrencyActivity.class);
        currencyActivity = activity.get();
    }

    @Test
    public void abrirActivityLocation() {
        Assert.assertNotNull(currencyActivity);
    }

}

