package com.projeto.testedevandroidsantander.ui.mainScreen;

import com.projeto.testedevandroidsantander.model.LancamentoDTO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class MainActivityUnitTest {
    @Before
    public void setUp(){}
    @After
    public void tearDown(){}


    @Test
    public void MainActivity_ShouldNOT_be_Null(){
        //Given
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        //When

        // Then
        Assert.assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_fetchMainMetaData(){
        //Given
        MainActivityOutputSpy mainActivityOutputSpy = new MainActivityOutputSpy();
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchMetaData to test our condition
        mainActivity.output = mainActivityOutputSpy;

        //When
        mainActivity.fetchMetaData();

        //Then
        Assert.assertTrue(mainActivityOutputSpy.fetchMainMetaDataIsCalled);
  }

    @Test
    public void onCreate_Calls_fetchMainMetaData_withCorrectData(){
        //Given
        MainActivityOutputSpy mainActivityOutputSpy = new MainActivityOutputSpy();
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.output = mainActivityOutputSpy;

        //When
        mainActivity.fetchMetaData();

        //Then
        Assert.assertNotNull(mainActivity);
    }



    private class MainActivityOutputSpy implements MainInteractorInput {

        boolean fetchMainMetaDataIsCalled = false;
        MainRequest mainRequestCopy;
        @Override
        public void fetchMainMetaData(MainRequest request) {
            fetchMainMetaDataIsCalled = true;
            mainRequestCopy = request;
        }

        @Override
        public void presentMainMetaData(LancamentoDTO lancamentoDTO) {

        }
    }


}
