package com.luizroseiro.testeandroidv2.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class UtilsUnitTest {

    @Test
    public void isValidPasswordReturnsTrue() {
        Assert.assertTrue(Utils.isValidPassword("2F&"));
    }

    @Test
    public void isValidPasswordReturnsFalse() {
        Assert.assertFalse(Utils.isValidPassword("13151687ceu"));
    }

}
