package com.example.testeandroidv2.statementScreen;

import com.example.testeandroidv2.loginScreen.UserModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk=19)
public class StatementActivityUnitTest {
    StatementActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(StatementActivity.class).get();
    }

    @After
    public void setDown(){
        activity.finish();
    }

    @Test
    public void StatementActivity_ShouldNOT_be_null(){
        Assert.assertNotNull(activity);
    }

    @Test
    public void fetchMetaData_with_userModelNOT_Null_ShouldCall_fetchClientData(){
        // Given
        StatementActivityOutputSpy outputSpy = new StatementActivityOutputSpy();

        // Setup Double Test
        activity.userModel = new UserModel(1, "Teste", "123", "456789", 3.3345);
        activity.output = outputSpy;

        // When
        activity.fetchMetaData();

        // Then
        Assert.assertTrue(outputSpy.fetchClientDataIsCalled);
    }

    @Test
    public void fetchMetaData_with_userModel_Null_ShouldNOT_Call_fetchClientData(){
        // Given
        StatementActivityOutputSpy outputSpy = new StatementActivityOutputSpy();

        // Setup Double Test
        activity.output = outputSpy;

        // When
        activity.fetchMetaData();

        // Then
        Assert.assertFalse(outputSpy.fetchClientDataIsCalled);
    }

    @Test
    public void fetchMetaData_with_userModelNOT_Null_ShouldCall_fetchStatementData(){
        // Given
        StatementActivityOutputSpy outputSpy = new StatementActivityOutputSpy();

        // Setup Double Test
        activity.userModel = new UserModel(1, "Teste", "123", "456789", 3.3345);
        activity.output = outputSpy;

        // When
        activity.fetchMetaData();

        // Then
        Assert.assertTrue(outputSpy.fetchStatementDataIsCalled);
    }

    @Test
    public void fetchMetaData_with_userModel_Null_ShouldNOT_Call_fetchStatementData(){
        // Given
        StatementActivityOutputSpy outputSpy = new StatementActivityOutputSpy();

        // Setup Double Test
        activity.output = outputSpy;

        // When
        activity.fetchMetaData();

        // Then
        Assert.assertFalse(outputSpy.fetchStatementDataIsCalled);
    }
}

class StatementActivityOutputSpy implements StatementInteractorInput {

    Boolean fetchStatementDataIsCalled = false;
    Boolean fetchClientDataIsCalled = false;

    @Override
    public void fetchStatementData(StatementRequest request) {
        fetchStatementDataIsCalled = true;
    }

    @Override
    public void fetchClientData(ClientRequest clientRequest) {
        fetchClientDataIsCalled = true;
    }
}
