package com.projeto.testedevandroidsantander.ui.mainScreen;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

@RunWith(RobolectricTestRunner.class)
public class MainPresenterUnitTest {
    public static String TAG = MainPresenterUnitTest.class.getSimpleName();

    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void presentMainMetaData_with_vaildInput_shouldCall_displayMainMetaData(){
        //Given
        MainPresenter mainPresenter = new MainPresenter();
        MainResponse mainResponse = new MainResponse();
        new LancamentoWorker().getLancamentosByIdUser(1);

        MainActivityInputSpy mainActivityInputSpy = new MainActivityInputSpy();
        mainPresenter.output = new WeakReference<MainActivityInput>(mainActivityInputSpy);

        //When
        mainPresenter.presentMainMetaData(mainResponse);

        //Then
        Assert.assertTrue("When the valid input is passed to MainPresenter Then displayMainMetaData should be called", mainActivityInputSpy.isdisplayMainMetaDataCalled);
    }

    @Test
    public void presentMainMetaData_with_inVaildInput_shouldNotCall_displayMainMetaData(){
        //Given
        MainPresenter mainPresenter = new MainPresenter();
        MainResponse mainResponse = new MainResponse();
        mainResponse.lancamentos = null;

        MainActivityInputSpy mainActivityInputSpy = new MainActivityInputSpy();
        mainPresenter.output = new WeakReference<MainActivityInput>(mainActivityInputSpy);

        //When
        mainPresenter.presentMainMetaData(mainResponse);

        //Then
        Assert.assertFalse("When the valid input is passed to MainPresenter Then displayMainMetaData should NOT be called", mainActivityInputSpy.isdisplayMainMetaDataCalled);
    }

    private class MainActivityInputSpy implements MainActivityInput {
        public boolean isdisplayMainMetaDataCalled = false;
        public MainViewModel mainViewModelCopy;
        @Override
        public void displayMainMetaData(MainViewModel mainViewModel) {
            isdisplayMainMetaDataCalled = true;
            mainViewModelCopy = mainViewModel;
        }

        @Override
        public void showProgressBar() {

        }

        @Override
        public void hideProgressBar() {

        }
    }
}
