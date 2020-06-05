package com.bank.testeandroidv2.statementScreen;//package com.testeandroidv2.bank.statementScreen;
//
//import android.util.Log;
//
//import com.mycompany.flightstatuslistview.FlightModel;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.RobolectricTestRunner;
//
//import java.lang.ref.WeakReference;
//import java.util.ArrayList;
//import java.util.Calendar;
//
//@RunWith(RobolectricTestRunner.class)
//public class CurrencyPresenterUnitTest {
//    public static String TAG = CurrencyPresenterUnitTest.class.getSimpleName();
//
//    @Before
//    public void setUp(){
//
//    }
//    @After
//    public void tearDown(){
//
//    }
//
//    @Test
//    public void presentCurrencyMetaData_with_vaildInput_shouldCall_displayCurrencyMetaData(){
//        //Given
//        CurrencyPresenter currencyPresenter = new CurrencyPresenter();
//        CurrencyResponse currencyResponse = new CurrencyResponse();
//        currencyResponse.listOfFlights = new FlightWorker().getFutureFlights();
//
//        CurrencyActivityInputSpy currencyActivityInputSpy = new CurrencyActivityInputSpy();
//        currencyPresenter.output = new WeakReference<CurrencyActivityInput>(currencyActivityInputSpy);
//
//        //When
//        currencyPresenter.presentCurrencyMetaData(currencyResponse);
//
//        //Then
//        Assert.assertTrue("When the valid input is passed to CurrencyPresenter Then displayCurrencyMetaData should be called", currencyActivityInputSpy.isdisplayCurrencyMetaDataCalled);
//    }
//
//    @Test
//    public void presentCurrencyMetaData_with_inVaildInput_shouldNotCall_displayCurrencyMetaData(){
//        //Given
//        CurrencyPresenter currencyPresenter = new CurrencyPresenter();
//        CurrencyResponse currencyResponse = new CurrencyResponse();
//        currencyResponse.listOfFlights = null;
//
//        CurrencyActivityInputSpy currencyActivityInputSpy = new CurrencyActivityInputSpy();
//        currencyPresenter.output = new WeakReference<CurrencyActivityInput>(currencyActivityInputSpy);
//
//        //When
//        currencyPresenter.presentCurrencyMetaData(currencyResponse);
//
//        //Then
//        Assert.assertFalse("When the valid input is passed to CurrencyPresenter Then displayCurrencyMetaData should NOT be called", currencyActivityInputSpy.isdisplayCurrencyMetaDataCalled);
//    }
//
//    @Test
//    public void verify_CurrencyPresenter_getDaysDiff_is_CalcualtedCorrectly_ForFutureTrips(){
//        //Given
//        CurrencyPresenter currencyPresenter = new CurrencyPresenter();
//        CurrencyResponse currencyResponse = new CurrencyResponse();
//
//        ArrayList<FlightModel> flightsList = new ArrayList<>();
//
//        FlightModel flight1 = new FlightModel();
//        flight1.flightName = "9Z 231";
//        flight1.startingTime = "2017/12/31";
//        flight1.departureCity = "BLR";
//        flight1.arrivalCity = "CJB";
//        flight1.departureTime = "18:10";
//        flight1.arrivalTime = "19:00";
//        flightsList.add(flight1);
//        currencyResponse.listOfFlights = flightsList;
//
//        CurrencyActivityInputSpy currencyActivityInputSpy = new CurrencyActivityInputSpy();
//        currencyPresenter.output = new WeakReference<CurrencyActivityInput>(currencyActivityInputSpy);
//
//        //When
//        Calendar currentTime = Calendar.getInstance();
//        currentTime.set(2017,5,30,0,0,0);
//        currencyPresenter.setCurrentTime(currentTime);
//        currencyPresenter.presentCurrencyMetaData(currencyResponse);
//
//        //Then
//        // "It has been " + daysDiff + " days since you flew";
//        String ExpectedText = "You have " + "184" + " days to fly";
//        String ActualText = currencyActivityInputSpy.currencyViewModelCopy.listOfFlights.get(0).noOfDaysToFly;
//        Assert.assertEquals("When current date is 2016/10/12 & Flying Date is 2016/10/31 Then no of days should be 19",ExpectedText,ActualText);
//
//    }
//
//    @Test
//    public void verify_CurrencyPresenter_getDaysDiff_is_CalcualtedCorrectly_ForPastTrips(){
//        //Given
//        CurrencyPresenter currencyPresenter = new CurrencyPresenter();
//        CurrencyResponse currencyResponse = new CurrencyResponse();
//
//        ArrayList<FlightModel> flightsList = new ArrayList<>();
//
//        FlightModel flight1 = new FlightModel();
//        flight1.flightName = "9Z 231";
//        flight1.startingTime = "2016/10/01";
//        flight1.departureCity = "BLR";
//        flight1.arrivalCity = "CJB";
//        flight1.departureTime = "18:10";
//        flight1.arrivalTime = "19:00";
//
//        flightsList.add(flight1);
//
//
//        currencyResponse.listOfFlights = flightsList;
//
//        CurrencyActivityInputSpy currencyActivityInputSpy = new CurrencyActivityInputSpy();
//        currencyPresenter.output = new WeakReference<CurrencyActivityInput>(currencyActivityInputSpy);
//
//
//        //When
//        Calendar currentTime = Calendar.getInstance();
//        //currentTime.set(2017,5,30,0,0,0);
//        currentTime.set(2017,5,30);
//        Log.e(TAG, "verify_CurrencyPresenter_getDaysDiff_is_CalcualtedCorrectly_ForPastTrips: "+currentTime.toString() );
//        currencyPresenter.setCurrentTime(currentTime);
//        currencyPresenter.presentCurrencyMetaData(currencyResponse);
//
//
//        //Then
//        // "It has been " + daysDiff + " days since you flew";
//        String ExpectedText = "It has been " + 272 + " days since you flew";
//        String ActualText = currencyActivityInputSpy.currencyViewModelCopy.listOfFlights.get(0).noOfDaysToFly;
//        Assert.assertEquals("When current date is 2017/05/30 & Flying Date is 2016/10/01 Then no of days should be 271",ExpectedText,ActualText);
//
//    }
//
//    private class CurrencyActivityInputSpy implements CurrencyActivityInput {
//        public boolean isdisplayCurrencyMetaDataCalled = false;
//        public CurrencyViewModel currencyViewModelCopy;
//        @Override
//        public void displayCurrencyMetaData(CurrencyViewModel currencyViewModel) {
//            isdisplayCurrencyMetaDataCalled = true;
//            currencyViewModelCopy = currencyViewModel;
//        }
//    }
//}
