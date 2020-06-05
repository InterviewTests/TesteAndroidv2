package com.bank.testeandroidv2.statementScreen;//package com.testeandroidv2.bank.statementScreen;
//
//import android.support.annotation.NonNull;
//
//import com.mycompany.flightstatuslistview.ArrayEmptyException;
//import com.mycompany.flightstatuslistview.FlightModel;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.RobolectricTestRunner;
//
//import java.util.ArrayList;
//
//@RunWith(RobolectricTestRunner.class)
//public class CurrencyInteractorUnitTest {
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
//    public void fetchCurrencyMetaData_with_vaildInput_shouldCall_presentCurrencyMetaData(){
//        //Given
//        CurrencyInteractor currencyInteractor = new CurrencyInteractor();
//        CurrencyRequest currencyRequest = new CurrencyRequest();
//        currencyRequest.isFutureTrips = true;
//        CurrencyPresenterInputSpy currencyPresenterInputSpy = new CurrencyPresenterInputSpy();
//        currencyInteractor.output = currencyPresenterInputSpy;
//        //When
//        currencyInteractor.fetchCurrencyMetaData(currencyRequest);
//
//        //Then
//        Assert.assertTrue("When the valid input is passed to CurrencyInteractor " +
//                        "Then presentCurrencyMetaData should be called",
//                 currencyPresenterInputSpy.presentCurrencyMetaDataIsCalled);
//    }
//
//    @Test
//    public void fetchCurrencyMetaData_with_vaildInput_FutureTrip_shouldCall_Worker_getFutureTrips(){
//        //Given
//        CurrencyInteractor currencyInteractor = new CurrencyInteractor();
//        CurrencyRequest currencyRequest = new CurrencyRequest();
//        currencyRequest.isFutureTrips = true;
//
//        //Setup TestDoubles
//        currencyInteractor.output = new CurrencyPresenterInputSpy();
//        FlightWorkerInputSpy flightWorkerInputSpy = new FlightWorkerInputSpy();
//        currencyInteractor.setFlightWorkerInput(flightWorkerInputSpy);
//
//        //When
//        currencyInteractor.fetchCurrencyMetaData(currencyRequest);
//
//        //Then
//        Assert.assertTrue("When the input is passed to CurrencyInteractor is FutureTrip" +
//                "Then getFutureFlights should be called in Worker",
//                flightWorkerInputSpy.isgetFutureFlightsMethodCalled);
//    }
//
//    @Test
//    public void fetchCurrencyMetaData_with_vaildInput_PastTrip_shouldCall_Worker_getPastTrips(){
//        //Given
//        CurrencyInteractor currencyInteractor = new CurrencyInteractor();
//        CurrencyRequest currencyRequest = new CurrencyRequest();
//        currencyRequest.isFutureTrips = false;
//
//        //Setup TestDoubles
//        currencyInteractor.output = new CurrencyPresenterInputSpy();
//        FlightWorkerInputSpy flightWorkerInputSpy = new FlightWorkerInputSpy();
//        currencyInteractor.setFlightWorkerInput(flightWorkerInputSpy);
//
//        //When
//        currencyInteractor.fetchCurrencyMetaData(currencyRequest);
//
//        //Then
//        Assert.assertTrue("When the input is passed to CurrencyInteractor is FutureTrip" +
//                        "Then getFutureFlights should be called in Worker",
//                flightWorkerInputSpy.isgetPastFlightsMethodCalled);
//    }
//
//
//    @Test(expected = ArrayEmptyException.class)
//    public void fetchCurrencyMetaData_fetchingNull_shouldThrowArrayEmptyException(){
//        //Given
//        CurrencyInteractor currencyInteractor = new CurrencyInteractor();
//        CurrencyRequest currencyRequest = new CurrencyRequest();
//        currencyRequest.isFutureTrips = false;
//
//        //Setup TestDoubles
//        currencyInteractor.output = new CurrencyPresenterInputSpy();
//        FlightWorkerInputReturnNullSpy flightWorkerInputReturnNullSpy = new FlightWorkerInputReturnNullSpy();
//        currencyInteractor.setFlightWorkerInput(flightWorkerInputReturnNullSpy);
//
//        //When
//        currencyInteractor.fetchCurrencyMetaData(currencyRequest);
//
//        //Then
////      // Check for ArrayEmptyException -- See this method Annotation
//    }
//
//    private class CurrencyPresenterInputSpy implements CurrencyPresenterInput {
//
//        boolean presentCurrencyMetaDataIsCalled = false;
//        CurrencyResponse currencyResponseCopy;
//        @Override
//        public void presentCurrencyMetaData(CurrencyResponse response) {
//            presentCurrencyMetaDataIsCalled = true;
//            currencyResponseCopy = response;
//        }
//    }
//
//    private class FlightWorkerInputSpy implements FlightWorkerInput {
//
//        boolean isgetFutureFlightsMethodCalled = false;
//        boolean isgetPastFlightsMethodCalled = false;
//
//        @Override
//        public ArrayList<FlightModel> getFutureFlights() {
//            isgetFutureFlightsMethodCalled = true;
//            return getFlightModels();
//        }
//
//        @Override
//        public ArrayList<FlightModel> getPastFlights() {
//            isgetPastFlightsMethodCalled = true;
//            return getFlightModels();
//        }
//
//        @NonNull
//        private ArrayList<FlightModel> getFlightModels() {
//            ArrayList<FlightModel> flightsList = new ArrayList<>();
//            FlightModel flight1 = new FlightModel();
//            flight1.flightName = "9Z 231";
//            flight1.startingTime = "2016/10/31";
//            flight1.departureCity = "BLR";
//            flight1.arrivalCity = "CJB";
//            flight1.departureTime = "18:10";
//            flight1.arrivalTime = "19:00";
//            flightsList.add(flight1);
//            return flightsList;
//        }
//    }
//
//    private class FlightWorkerInputReturnNullSpy implements FlightWorkerInput {
//
//        boolean isgetFlightsMethodCalled = false;
//        boolean isgetPastFlightsMethodCalled = false;
//
//        @Override
//        public ArrayList<FlightModel> getFutureFlights() {
//            isgetFlightsMethodCalled = true;
//            return null;
//        }
//
//        @Override
//        public ArrayList<FlightModel> getPastFlights() {
//            isgetPastFlightsMethodCalled = true;
//            return null;
//        }
//    }
//}
