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

    @Test
    public void formatAgencyReturnFormatted() {
        Assert.assertEquals("12.345678-9", Utils.formatAgency("123456789"));
    }

    @Test
    public void formatAgencyReturnNotFormatted() {
        Assert.assertEquals("123465", Utils.formatAgency("123465"));
    }

    @Test
    public void formatDateReturnFormatted() {
        Assert.assertEquals("27/07/2018", Utils.formatDate("2018-07-27"));
    }

    @Test
    public void formatDateReturnNotFormatted() {
        Assert.assertEquals("07-27", Utils.formatDate("07-27"));
    }

}