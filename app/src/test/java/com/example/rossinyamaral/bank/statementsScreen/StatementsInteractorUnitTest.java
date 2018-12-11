package com.example.rossinyamaral.bank.statementsScreen;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class StatementsInteractorUnitTest {

    @Test
    public void fetchData_with_validInput_shouldCall_presentLoginMetaData() {
        //Given
        StatementsInteractorInputSpy interactor = new StatementsInteractorInputSpy();
        StatementsRequest request = new StatementsRequest();
        request.userId = 1;
        StatementsPresenterInputSpy presenterInputSpy = new StatementsPresenterInputSpy();
        interactor.output = presenterInputSpy;
        //When
        interactor.fetchStatementsData(request);

        //Then
        Assert.assertTrue("When the valid input is passed to Interactor " +
                        "Then presentData should be called",
                interactor.interactorDataIsCalled);
    }

    @Test
    public void fetchData_with_validInput_shouldCall_presentData() {
        //Given
        StatementsInteractorInputSpy interactorInputSpy = new StatementsInteractorInputSpy();
        StatementsRequest request = new StatementsRequest();
        request.userId = 1;

        //Setup TestDoubles
        interactorInputSpy.output = new StatementsPresenterInputSpy();

        //When
        interactorInputSpy.fetchStatementsData(request);

        //Then
        Assert.assertTrue("When the request is passed to LoginInteractor" +
                        "Then presentLoginData should be called",
                interactorInputSpy.interactorDataIsCalled);
    }

    private class StatementsPresenterInputSpy implements StatementsPresenterInput {

        boolean presentDataIsCalled = false;
        StatementsResponse statementsResponseCopy;

        @Override
        public void presentStatementsData(StatementsResponse response) {
            presentDataIsCalled = true;
            statementsResponseCopy = response;
        }
    }

    private class StatementsInteractorInputSpy implements StatementsInteractorInput {

        boolean interactorDataIsCalled = false;
        StatementsRequest requestCopy;
        StatementsPresenterInput output;

        @Override
        public void fetchStatementsData(StatementsRequest request) {
            interactorDataIsCalled = true;
            requestCopy = request;
        }
    }
}
