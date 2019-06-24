package com.example.bankapp;

import com.example.bankapp.helper.MyConveter;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class MyConverterTest extends TestCase {

    @Test
    public void testFormatDateIsValid() {
        String text = MyConveter.formatDate("2019-02-21");
        Assert.assertThat(text, is(equalTo("21/02/2019")));
    }

    @Test
    public void testFormatAgencyIsValid() {
        String text = MyConveter.formatAgency("012314564");
        Assert.assertThat(text, is(equalTo("01.231456-4")));
    }

    @Test
    public void testFormatCurrency() {
        String text = MyConveter.formatCurrency(3.56667);
        Assert.assertThat(text, is(equalTo("$3.57")));
    }

}
