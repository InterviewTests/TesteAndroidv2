package com.casasw.bankapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class CurrencyInteractorUnitTest {

    @Before
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void fetchCurrencyData_with_validInput_shouldCall_Worker_getCurrencyData(){
        CurrencyInteractor currencyInteractor = new CurrencyInteractor();
        CurrencyRequest currencyRequest =  new CurrencyRequest();
        currencyRequest.isCurrencyOn = true;

        CurrencyPresenterInputSpy currencyPresenterInputSpy = new CurrencyPresenterInputSpy();
        currencyInteractor.output = currencyPresenterInputSpy;
        CurrencyWorkerInputSpy currencyWorkerInputSpy = new CurrencyWorkerInputSpy();
        currencyInteractor.setCurrencyWorkerInput(currencyWorkerInputSpy);

        currencyInteractor.fetchCurrencyData(currencyRequest);

        Assert.assertTrue(currencyWorkerInputSpy.isgetCurrencyDataMethodCalled);
    }

    @Test
    public void fetchCurrencyMetaData_with_validInput_shouldCall_presentCurrencyData(){

        CurrencyInteractor currencyInteractor = new CurrencyInteractor();
        CurrencyRequest currencyRequest = new CurrencyRequest();
        currencyRequest.isCurrencyOn = true;
        CurrencyPresenterInputSpy currencyPresenterInputSpy = new CurrencyPresenterInputSpy();
        currencyInteractor.output = currencyPresenterInputSpy;

        currencyInteractor.fetchCurrencyData(currencyRequest);

        Assert.assertTrue(currencyPresenterInputSpy.presentCurrencyDataIsCalled);
    }



    public static class CurrencyWorkerInputSpy implements CurrencyWorkerInput {
        boolean isgetCurrencyDataMethodCalled = false;

        @Override
        public String getCurrencyData() {
            isgetCurrencyDataMethodCalled = true;
            return getCurrencyData();
        }


    }

    private class CurrencyPresenterInputSpy implements CurrencyPresenterInput {
        boolean presentCurrencyDataIsCalled = false;
        CurrencyResponse currencyResponseCopy;

        @Override
        public void presentCurrencyData(CurrencyResponse response) {
            presentCurrencyDataIsCalled = true;
            currencyResponseCopy = response;
        }
    }
}
