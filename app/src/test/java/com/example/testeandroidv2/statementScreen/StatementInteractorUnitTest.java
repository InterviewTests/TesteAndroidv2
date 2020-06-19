package com.example.testeandroidv2.statementScreen;

import com.example.testeandroidv2.Service.Api;
import com.example.testeandroidv2.Service.StatementService;
import com.example.testeandroidv2.loginScreen.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

public class StatementInteractorUnitTest {

    @Test
    public void fetchStatementData_with_validInput_shouldCall_StatementPresenterInput_presentStatementData(){
        // Given
        StatementInteractor statementInteractor = new StatementInteractor();
        StatementRequest statementRequest = new StatementRequest();
        statementRequest.user = new UserModel(1, "Teste", "123", "456789", 3.3345);
        StatementPresenterInputSpy statementPresenterInputSpy = new StatementPresenterInputSpy();

        // Setup Test Double
        statementInteractor.output = statementPresenterInputSpy;
        StatementWorkerInputSpy statementWorkerInputSpy = new StatementWorkerInputSpy();
        statementInteractor.setStatementWorkerInput(statementWorkerInputSpy);

        // When
        statementInteractor.fetchStatementData(statementRequest);

        // Then
        Assert.assertTrue(statementPresenterInputSpy.presentStatementDataIsCalled);
    }

    @Test
    public void fetchClientData_with_validInput_shouldCall_StatementPresenterInput_presentClientData(){
        // Given
        StatementInteractor statementInteractor = new StatementInteractor();
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.userModel = new UserModel(1, "Teste", "123", "456789", 3.3345);
        StatementPresenterInputSpy statementPresenterInputSpy = new StatementPresenterInputSpy();

        // Setup Test Double
        statementInteractor.output = statementPresenterInputSpy;
        StatementWorkerInputSpy statementWorkerInputSpy = new StatementWorkerInputSpy();
        statementInteractor.setStatementWorkerInput(statementWorkerInputSpy);

        // When
        statementInteractor.fetchClientData(clientRequest);

        // Then
        Assert.assertTrue(statementPresenterInputSpy.presentClientDataIsCalled);
    }

    @Test
    public void fetchStatementData_with_invalidInput_shouldCall_StatementPresenterInput_presentStatementData(){
        // Given
        StatementInteractor statementInteractor = new StatementInteractor();
        StatementRequest statementRequest = new StatementRequest();
        StatementPresenterInputSpy statementPresenterInputSpy = new StatementPresenterInputSpy();

        // Setup Test Double
        statementInteractor.output = statementPresenterInputSpy;
        StatementWorkerInputSpy statementWorkerInputSpy = new StatementWorkerInputSpy();
        statementInteractor.setStatementWorkerInput(statementWorkerInputSpy);

        // When
        statementInteractor.fetchStatementData(statementRequest);

        // Then
        Assert.assertTrue(statementPresenterInputSpy.presentStatementDataIsCalled);
    }

    @Test
    public void fetchClientData_with_invalidInput_shouldCall_StatementPresenterInput_presentClientData(){
        // Given
        StatementInteractor statementInteractor = new StatementInteractor();
        ClientRequest clientRequest = new ClientRequest();
        StatementPresenterInputSpy statementPresenterInputSpy = new StatementPresenterInputSpy();

        // Setup Test Double
        statementInteractor.output = statementPresenterInputSpy;
        StatementWorkerInputSpy statementWorkerInputSpy = new StatementWorkerInputSpy();
        statementInteractor.setStatementWorkerInput(statementWorkerInputSpy);

        // When
        statementInteractor.fetchClientData(clientRequest);

        // Then
        Assert.assertTrue(statementPresenterInputSpy.presentClientDataIsCalled);
    }

    @Test
    public void fetchStatementData_with_validInput_shouldCall_Worker_buildRequest(){
        // Given
        StatementInteractor statementInteractor = new StatementInteractor();
        StatementRequest statementRequest = new StatementRequest();
        statementRequest.user = new UserModel(1, "Teste", "123", "456789", 3.3345);

        // Setup Test Double
        statementInteractor.output = new StatementPresenterInputSpy();
        StatementWorkerInputSpy statementWorkerInputSpy = new StatementWorkerInputSpy();
        statementInteractor.setStatementWorkerInput(statementWorkerInputSpy);

        // When
        statementInteractor.fetchStatementData(statementRequest);

        // Then
        Assert.assertTrue(statementWorkerInputSpy.buildRequestIsCalled);
    }

    @Test
    public void fetchStatementData_with_validInput_shouldCall_Worker_getStatements(){
        // Given
        StatementInteractor statementInteractor = new StatementInteractor();
        StatementRequest statementRequest = new StatementRequest();
        statementRequest.user = new UserModel(1, "Teste", "123", "456789", 3.3345);

        // Setup Test Double
        statementInteractor.output = new StatementPresenterInputSpy();
        StatementWorkerInputSpy statementWorkerInputSpy = new StatementWorkerInputSpy();
        statementInteractor.setStatementWorkerInput(statementWorkerInputSpy);

        // When
        statementInteractor.fetchStatementData(statementRequest);

        // Then
        Assert.assertTrue(statementWorkerInputSpy.getStatementResponseIsCalled);
    }

    @Test
    public void fetchStatementData_with_invalidInput_shouldNOT_Call_Worker_buildRequest(){
        // Given
        StatementInteractor statementInteractor = new StatementInteractor();
        StatementRequest statementRequest = new StatementRequest();

        // Setup Test Double
        statementInteractor.output = new StatementPresenterInputSpy();
        StatementWorkerInputSpy statementWorkerInputSpy = new StatementWorkerInputSpy();
        statementInteractor.setStatementWorkerInput(statementWorkerInputSpy);

        // When
        statementInteractor.fetchStatementData(statementRequest);

        // Then
        Assert.assertFalse(statementWorkerInputSpy.buildRequestIsCalled);
    }

    @Test
    public void fetchStatementData_with_invalidInput_shouldNOT_Call_Worker_getStatements(){
        // Given
        StatementInteractor statementInteractor = new StatementInteractor();
        StatementRequest statementRequest = new StatementRequest();

        // Setup Test Double
        statementInteractor.output = new StatementPresenterInputSpy();
        StatementWorkerInputSpy statementWorkerInputSpy = new StatementWorkerInputSpy();
        statementInteractor.setStatementWorkerInput(statementWorkerInputSpy);

        // When
        statementInteractor.fetchStatementData(statementRequest);

        // Then
        Assert.assertFalse(statementWorkerInputSpy.getStatementResponseIsCalled);
    }
}

class StatementPresenterInputSpy implements StatementPresenterInput{

    Boolean presentStatementDataIsCalled = false;
    Boolean presentClientDataIsCalled = false;

    @Override
    public void presentStatementData(StatementResponse response) {
        presentStatementDataIsCalled = true;
    }

    @Override
    public void presentClientData(ClientResponse response) {
        presentClientDataIsCalled = true;
    }
}

class StatementWorkerInputSpy implements StatementWorkerInput{

    Boolean buildRequestIsCalled = false;
    Boolean getStatementResponseIsCalled = false;
    private Call<JsonObject> call;
    StatementResponse response;

    @Override
    public void buildRequest(StatementRequest request) {
        buildRequestIsCalled = true;
        Retrofit api = Api.getRetrofitInstance("https://bank-app-test.herokuapp.com/api/");
        StatementService statementService = api.create(StatementService.class);
        call = statementService.getStatements(request.user.getUserId());
    }

    @Override
    public StatementResponse getStatementsResponse(Callback<JsonObject> callback) {
        getStatementResponseIsCalled = true;
        try {
            response = new Gson().fromJson(call.execute().body(), StatementResponse.class);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
