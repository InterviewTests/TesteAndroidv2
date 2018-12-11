package com.example.rossinyamaral.bank.statementsScreen;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class StatementsActivityUnitTest {

    @Test
    public void StatementsActivity_ShouldNOT_be_Null(){
        //Given
        StatementsActivity activity = Robolectric.setupActivity(StatementsActivity.class);
        //When

        // Then
        Assert.assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_fetchMetaData(){
        //Given
        StatementsActivityOutputSpy statementsActivityOutputSpy = new StatementsActivityOutputSpy();
        StatementsActivity activity = Robolectric.setupActivity(StatementsActivity.class);
        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchMetaData to test our condition
        activity.output = statementsActivityOutputSpy;

        //When
        activity.output.fetchStatementsData(null);

        //Then
        Assert.assertTrue(statementsActivityOutputSpy.fetchMetaDataIsCalled);
    }

    @Test
    public void onCreate_Calls_fetchLoginMetaData_withCorrectData(){
        //Given
        StatementsActivityOutputSpy statementsActivityOutputSpy = new StatementsActivityOutputSpy();
        StatementsActivity activity = Robolectric.setupActivity(StatementsActivity.class);
        activity.output = statementsActivityOutputSpy;

        //When
        StatementsRequest request = new StatementsRequest();
        request.userId = 1;
        activity.output.fetchStatementsData(request);

        //Then
        Assert.assertNotNull(activity);
        Assert.assertEquals(statementsActivityOutputSpy.loginRequestCopy, request);
    }



    private class StatementsActivityOutputSpy implements StatementsInteractorInput {

        boolean fetchMetaDataIsCalled = false;
        StatementsRequest loginRequestCopy;
        @Override
        public void fetchStatementsData(StatementsRequest request) {
            fetchMetaDataIsCalled = true;
            loginRequestCopy = request;
        }
    }
}
