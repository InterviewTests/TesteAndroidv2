//package com.testeandroidv2.bank.statementScreen;
//
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//
//@RunWith(RobolectricTestRunner.class)
//public class CurrencyActivityUnitTest {
//    @Before
//    public void setUp(){}
//    @After
//    public void tearDown(){}
//
//
//    @Test
//    public void CurrencyActivity_ShouldNOT_be_Null(){
//        //Given
//        CurrencyActivity activity = Robolectric.setupActivity(CurrencyActivity.class);
//        //When
//
//        // Then
//        Assert.assertNotNull(activity);
//    }
//
//    @Test
//    public void onCreate_shouldCall_fetchCurrencyMetaData(){
//        //Given
//        CurrencyActivityOutputSpy currencyActivityOutputSpy = new CurrencyActivityOutputSpy();
//        CurrencyActivity currencyActivity = Robolectric.setupActivity(CurrencyActivity.class);
//        // It must have called the onCreate earlier,
//        // we are injecting the mock and calling the fetchMetaData to test our condition
//        currencyActivity.output = currencyActivityOutputSpy;
//
//        //When
//        currencyActivity.fetchMetaData();
//
//        //Then
//        Assert.assertTrue(currencyActivityOutputSpy.fetchCurrencyMetaDataIsCalled);
//  }
//
//    @Test
//    public void onCreate_Calls_fetchCurrencyMetaData_withCorrectData(){
//        //Given
//        CurrencyActivityOutputSpy currencyActivityOutputSpy = new CurrencyActivityOutputSpy();
//        CurrencyActivity currencyActivity = Robolectric.setupActivity(CurrencyActivity.class);
//        currencyActivity.output = currencyActivityOutputSpy;
//
//        //When
//        currencyActivity.fetchMetaData();
//
//        //Then
//        Assert.assertNotNull(currencyActivity);
//        Assert.assertTrue(currencyActivityOutputSpy.currencyRequestCopy.isFutureTrips);
//    }
//
//
//
//    private class CurrencyActivityOutputSpy implements CurrencyInteractorInput {
//
//        boolean fetchCurrencyMetaDataIsCalled = false;
//        CurrencyRequest currencyRequestCopy;
//        @Override
//        public void fetchCurrencyMetaData(CurrencyRequest request) {
//            fetchCurrencyMetaDataIsCalled = true;
//            currencyRequestCopy = request;
//        }
//    }
//
//
//}
