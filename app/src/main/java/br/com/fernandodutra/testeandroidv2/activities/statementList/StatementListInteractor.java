package br.com.fernandodutra.testeandroidv2.activities.statementList;

import android.content.Context;

import java.util.ArrayList;

import br.com.fernandodutra.testeandroidv2.models.ErrorMessage;
import br.com.fernandodutra.testeandroidv2.models.StatementList;
import br.com.fernandodutra.testeandroidv2.models.StatementListWorker;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 20:02
 * TesteAndroidv2_CleanCode
 */


interface StatementListInteractorInput {
    void fetchStatementListMetaData(Integer userId);
}

interface OnFinished {
    void onFinished(StatementListWorker statementListWorker);

    void onFailure(Throwable t);
}

public class StatementListInteractor implements StatementListInteractorInput, OnFinished {

    public StatementListPresenterInput statementListPresenterInput;
    public StatementListApiWorkerInput statementListApiWorkerInput;
    public StatementListResponse statementListResponse;
    //
    public static String TAG = StatementListInteractor.class.getSimpleName();

    private StatementListApiWorkerInput getLoginApiInput() {
        if (statementListApiWorkerInput == null) return new StatementListApiWorker();
        return statementListApiWorkerInput;
    }

    @Override
    public void fetchStatementListMetaData(Integer userId) {
        statementListApiWorkerInput = getLoginApiInput();
        statementListResponse = new StatementListResponse();

        statementListApiWorkerInput.getUserAccountResponse(this, userId);
    }

    @Override
    public void onFinished(StatementListWorker statementListWorker) {
        if (statementListWorker != null) {
            statementListResponse.statementListWorker = statementListWorker;
            statementListResponse.errorMessage = new ArrayList<>();
        } else {
            statementListResponse.statementListWorker = new StatementListWorker();
            statementListResponse.errorMessage = new ArrayList<>();
        }

        statementListPresenterInput.presentLoginMetaData(statementListResponse);
    }

    @Override
    public void onFailure(Throwable t) {
        Integer id = -1;
        String message = t.getMessage();

        statementListResponse.statementListWorker = new StatementListWorker();
        statementListResponse.errorMessage.add(new ErrorMessage(id, message));

        statementListPresenterInput.presentLoginMetaData(statementListResponse);
    }
}
