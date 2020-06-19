package com.example.testeandroidv2.statementScreen;

import com.example.testeandroidv2.loginScreen.UserModel;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class StatementPresenterUnitTest {

    @Test
    public void presentStatementData_ShouldCall_displayStatementData(){
        // Given
        StatementPresenter statementPresenter = new StatementPresenter();
        StatementResponse statementResponse = new StatementResponse();
        statementResponse.statementList = new ArrayList<>();
        statementResponse.statementList.add(new StatementModel("Teste", "Teste", "1999-01-01", "1000"));
        StatementActivityInputSpy statementActivityInputSpy = new StatementActivityInputSpy();

        // Setup Double Test
        statementPresenter.output = new WeakReference<>(statementActivityInputSpy);

        // When
        statementPresenter.presentStatementData(statementResponse);

        // Then
        Assert.assertTrue(statementActivityInputSpy.displayStatementDataIsCalled);
    }

    @Test
    public void presentStatementError_ShouldCall_displayStatementError(){
        // Given
        StatementPresenter statementPresenter = new StatementPresenter();
        StatementResponse statementResponse = new StatementResponse();
        StatementActivityInputSpy statementActivityInputSpy = new StatementActivityInputSpy();

        // Setup Double Test
        statementPresenter.output = new WeakReference<>(statementActivityInputSpy);

        // When
        statementPresenter.presentStatementData(statementResponse);

        // Then
        Assert.assertTrue(statementActivityInputSpy.displayStatementErrorIsCalled);
    }

    @Test
    public void presentClientData_with_validInput_ShouldCall_displayClientData(){
        // Given
        StatementPresenter statementPresenter = new StatementPresenter();
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.userModel = new UserModel(1, "Teste", "1234", "01235064", 3.3345);
        StatementActivityInputSpy statementActivityInputSpy = new StatementActivityInputSpy();

        // Setup Double Test
        statementPresenter.output = new WeakReference<>(statementActivityInputSpy);

        // When
        statementPresenter.presentClientData(clientResponse);

        // Then
        Assert.assertTrue(statementActivityInputSpy.displayClientDataIsCalled);
    }

    @Test
    public void presentClientData_with_invalidInput_ShouldNOT_Call_displayClientData(){
        // Given
        StatementPresenter statementPresenter = new StatementPresenter();
        ClientResponse clientResponse = new ClientResponse();
        StatementActivityInputSpy statementActivityInputSpy = new StatementActivityInputSpy();

        // Setup Double Test
        statementPresenter.output = new WeakReference<>(statementActivityInputSpy);

        // When
        statementPresenter.presentClientData(clientResponse);

        // Then
        Assert.assertFalse(statementActivityInputSpy.displayClientDataIsCalled);
    }

    @Test
    public void presentClientError_ShouldCall_displayClientError(){
        // Given
        StatementPresenter statementPresenter = new StatementPresenter();
        ClientResponse clientResponse = new ClientResponse();
        StatementActivityInputSpy statementActivityInputSpy = new StatementActivityInputSpy();

        // Setup Double Test
        statementPresenter.output = new WeakReference<>(statementActivityInputSpy);

        // When
        statementPresenter.presentClientData(clientResponse);

        // Then
        Assert.assertTrue(statementActivityInputSpy.displayClientErrorIsCalled);
    }

}

class StatementActivityInputSpy implements StatementActivityInput{

    Boolean displayStatementDataIsCalled = false;
    Boolean displayStatementErrorIsCalled = false;
    Boolean displayClientDataIsCalled = false;
    Boolean displayClientErrorIsCalled = false;

    @Override
    public void displayStatementData(List<StatementModel> statementModels) {
        displayStatementDataIsCalled = true;
    }

    @Override
    public void displayStatementError(Object error) {
        displayStatementErrorIsCalled = true;
    }

    @Override
    public void displayClientData(ClientModel clientModel) {
        displayClientDataIsCalled= true;
    }

    @Override
    public void displayClientError(Object error) {
        displayClientErrorIsCalled = true;
    }
}
