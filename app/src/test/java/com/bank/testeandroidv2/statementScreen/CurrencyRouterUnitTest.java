package com.bank.testeandroidv2.statementScreen;//package com.testeandroidv2.bank.statementScreen;
//
//
//import android.content.Intent;
//
//import com.mycompany.flightstatuslistview.FlightViewModel;
//import com.mycompany.flightstatuslistview.boardingScreen.BoardingActivity;
//import com.mycompany.flightstatuslistview.pastTripScreen.PastTripActivity;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//
//import java.lang.ref.WeakReference;
//import java.util.ArrayList;
//import java.util.Calendar;
//
//@RunWith(RobolectricTestRunner.class)
//public class CurrencyRouterUnitTest {
//    public static String TAG = CurrencyRouterUnitTest.class.getSimpleName();
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
//    public void currencyRouter_determineNextScreen_when_futureTripIs_Input() {
//        //Given
//        CurrencyRouter currencyRouter = new CurrencyRouter();
//        ArrayList<FlightViewModel> flightList = new ArrayList<>();
//        FlightViewModel flight1 = new FlightViewModel();
//        flight1.flightName = "9Z 231";
//        flight1.startingTime = "2017/12/31";
//        flight1.departureCity = "BLR";
//        flight1.arrivalCity = "CJB";
//        flight1.departureTime = "18:10";
//        flight1.arrivalTime = "19:00";
//        flightList.add(flight1);
//
//        FlightViewModel flight2 = new FlightViewModel();
//        flight2.flightName = "9Z 222";
//        flight2.startingTime = "2016/12/31";
//        flight2.departureCity = "BLR";
//        flight2.arrivalCity = "CJB";
//        flight2.departureTime = "18:10";
//        flight2.arrivalTime = "19:00";
//        flightList.add(flight2);
//
//        CurrencyActivity currencyActivity = Robolectric.setupActivity(CurrencyActivity.class);
//        currencyActivity.listOfVMFlights = flightList;
//        currencyActivity.router = currencyRouter;
//        currencyRouter.activity = new WeakReference<CurrencyActivity>(currencyActivity);
//
//        Calendar currentTime = Calendar.getInstance();
//        currentTime.set(2017,5,30,0,0,0);
//        currencyRouter.setCurrentTime(currentTime);
//
//
//        //When - Futrure Trip is Input
//
//        Intent intent = currencyRouter.determineNextScreen(0);
//
//        //Then
//        String targetActivityName = intent.getComponent().getClassName();
//        Assert.assertEquals("When the future travel date is passed to CurrencyRouter"
//                +" Then next Intent should be BoardingActivity",targetActivityName, BoardingActivity.class.getName());
//    }
//
//
//    @Test
//    public void currencyRouter_determineNextScreen_when_pastTripIs_Input() {
//        //Given
//        CurrencyRouter currencyRouter = new CurrencyRouter();
//        ArrayList<FlightViewModel> flightList = new ArrayList<>();
//        FlightViewModel flight1 = new FlightViewModel();
//        flight1.flightName = "9Z 231";
//        flight1.startingTime = "2017/12/31";
//        flight1.departureCity = "BLR";
//        flight1.arrivalCity = "CJB";
//        flight1.departureTime = "18:10";
//        flight1.arrivalTime = "19:00";
//        flightList.add(flight1);
//
//        FlightViewModel flight2 = new FlightViewModel();
//        flight2.flightName = "9Z 222";
//        flight2.startingTime = "2016/12/31";
//        flight2.departureCity = "BLR";
//        flight2.arrivalCity = "CJB";
//        flight2.departureTime = "18:10";
//        flight2.arrivalTime = "19:00";
//        flightList.add(flight2);
//
//        CurrencyActivity currencyActivity = Robolectric.setupActivity(CurrencyActivity.class);
//        currencyActivity.listOfVMFlights = flightList;
//        currencyActivity.router = currencyRouter;
//        currencyRouter.activity = new WeakReference<CurrencyActivity>(currencyActivity);
//
//        Calendar currentTime = Calendar.getInstance();
//        currentTime.set(2017,5,30,0,0,0);
//        currencyRouter.setCurrentTime(currentTime);
//
//
//
//        //When - Past Trip is Input
//        Intent intent = currencyRouter.determineNextScreen(1);
//
//        //Then
//        String targetActivityName = intent.getComponent().getClassName();
//        Assert.assertEquals("When the past travel date is passed to CurrencyRouter"
//                +" Then next Intent should be PastTripActivity",targetActivityName, PastTripActivity.class.getName());
//    }
//
//
//
//}
