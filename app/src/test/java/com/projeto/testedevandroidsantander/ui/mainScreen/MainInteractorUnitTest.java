package com.projeto.testedevandroidsantander.ui.mainScreen;

import android.support.annotation.NonNull;

import com.projeto.testedevandroidsantander.model.LancamentoModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
public class MainInteractorUnitTest {
    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void fetchMainMetaData_with_vaildInput_shouldCall_presentMainMetaData(){
        //Given
        MainInteractor mainInteractor = new MainInteractor();
        MainRequest mainRequest = new MainRequest();
        MainPresenterInputSpy mainPresenterInputSpy = new MainPresenterInputSpy();
        mainInteractor.output = mainPresenterInputSpy;
        //When
        mainInteractor.fetchMainMetaData(mainRequest);

        //Then
        Assert.assertTrue("When the valid input is passed to MainInteractor " +
                        "Then presentMainMetaData should be called",
                 mainPresenterInputSpy.presentMainMetaDataIsCalled);
    }

    private class MainPresenterInputSpy implements MainPresenterInput {

        boolean presentMainMetaDataIsCalled = false;
        MainResponse mainResponseCopy;
        @Override
        public void presentMainMetaData(MainResponse response) {
            presentMainMetaDataIsCalled = true;
            mainResponseCopy = response;
        }

        @Override
        public void visibleProgressBar() {

        }

        @Override
        public void hideProgressBar() {

        }
    }

    private class LancamentoWorkerInputSpy implements LancamentoWorkerInput {


        @NonNull
        private ArrayList<LancamentoModel> getLancamentoModels() {
            ArrayList<LancamentoModel> lancamentosList = new ArrayList<>();
            LancamentoModel lancamento1 = new LancamentoModel();
            lancamentosList.add(lancamento1);
            return lancamentosList;
        }

        @Override
        public void getLancamentosByIdUser(Integer idUser) {

        }
    }

    private class LancamentoWorkerInputReturnNullSpy implements LancamentoWorkerInput {

        @Override
        public void getLancamentosByIdUser(Integer idUser) {

        }
    }
}
