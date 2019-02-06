package com.luizroseiro.testeandroidv2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityUnitTest {

    @Test
    public void MainActivityNotNull() {
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).get();
        Assert.assertNotNull(mainActivity);
    }

}