package com.santander.vicolmoraes.santander;

import com.santander.vicolmoraes.santander.View.LocationActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class LocationTest {

    private LocationActivity locationActivity;
    private ActivityController<LocationActivity> activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(LocationActivity.class);
        locationActivity = activity.get();
    }

    @Test
    public void abrirActivityLocation() {
        Assert.assertNotNull(locationActivity);
    }

}

