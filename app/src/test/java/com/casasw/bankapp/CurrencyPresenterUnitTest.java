package com.casasw.bankapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

@RunWith(RobolectricTestRunner.class)
public class CurrencyPresenterUnitTest {
    public static String TAG = CurrencyPresenterUnitTest.class.getSimpleName();

    @Before
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void presentCurrencyMetaData_with_vaildInput_shouldCall_displayCurrencyData(){
        //Given
        CurrencyPresenter currencyPresenter = new CurrencyPresenter();
        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.currencyJSON = new CurrencyWorker().getCurrencyData();

        CurrencyActivityInputSpy currencyActivityInputSpy = new CurrencyActivityInputSpy();
        currencyPresenter.output = new WeakReference<CurrencyActivityInput>(currencyActivityInputSpy);

        //When
        currencyPresenter.presentCurrencyData(currencyResponse);

        //Then
        Assert.assertTrue("When the valid input is passed to CurrencyPresenter Then displayCurrencyMetaData should be called", currencyActivityInputSpy.isdisplayCurrencyMetaDataCalled);
    }

    @Test
    public void verify_CurrencyPresenter(){
        CurrencyPresenter currencyPresenter = new CurrencyPresenter();
        CurrencyResponse currencyResponse = new CurrencyResponse();
        CurrencyModel  currencyModel = new CurrencyModel();
        /*popular currency*/

        CurrencyActivityInputSpy currencyActivityInputSpy = new CurrencyActivityInputSpy();
        currencyPresenter.output =  new WeakReference<CurrencyActivityInput>(currencyActivityInputSpy);
        /*popular presenter*/

        /*então contruir string esperadas e testar assert*/

    }

    private class CurrencyActivityInputSpy implements CurrencyActivityInput {
        public boolean isdisplayCurrencyMetaDataCalled = false;
        public CurrencyViewModel currencyViewModelCopy;
        @Override
        public void displayCurrencyData(CurrencyViewModel currencyViewModel) {
            isdisplayCurrencyMetaDataCalled = true;
            currencyViewModelCopy = currencyViewModel;
        }
    }
    
}
