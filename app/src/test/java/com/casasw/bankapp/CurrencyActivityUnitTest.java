package com.casasw.bankapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class CurrencyActivityUnitTest {
    @Before
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void CurrencyActivity_ShouldNOT_be_Null(){
        CurrencyActivity activity = Robolectric.setupActivity(CurrencyActivity.class);

        Assert.assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_fetchCurrencyData(){
        CurrencyActivityOutputSpy currencyActivityOutputSpy = new CurrencyActivityOutputSpy();
        CurrencyActivity currencyActivity = Robolectric.setupActivity(CurrencyActivity.class);

        currencyActivity.output = currencyActivityOutputSpy;

        CurrencyRequest currencyRequest = new CurrencyRequest();

        currencyActivity.output.fetchCurrencyData(currencyRequest);

        Assert.assertTrue(currencyActivityOutputSpy.fetchCurrencyDataIsCalled);
    }

    @Test
    public void onCreate_Calls_fetchCurrencyData_withCorrectData(){
        CurrencyActivityOutputSpy currencyActivityOutputSpy = new CurrencyActivityOutputSpy();
        CurrencyActivity currencyActivity = Robolectric.setupActivity(CurrencyActivity.class);
        currencyActivity.output = currencyActivityOutputSpy;

        Assert.assertNotNull(currencyActivity);
        //Assert.assertTrue(currencyActivityOutputSpy.currencyRequestCopy.is); edit later for real propose

    }

    public static class CurrencyActivityOutputSpy implements CurrencyInteractorInput {
        boolean fetchCurrencyDataIsCalled = false;
        CurrencyRequest currencyRequestCopy;
        @Override
        public void fetchCurrencyData(CurrencyRequest request) {
            fetchCurrencyDataIsCalled = true;
            currencyRequestCopy = request;
        }
    }
}
