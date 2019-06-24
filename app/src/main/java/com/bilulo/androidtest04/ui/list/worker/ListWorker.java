package com.bilulo.androidtest04.ui.list.worker;

import com.bilulo.androidtest04.data.api.ResponseListener;
import com.bilulo.androidtest04.data.api.statements.StatementsService;
import com.bilulo.androidtest04.data.model.response.StatementsResponse;
import com.bilulo.androidtest04.ui.list.contract.ListContract;
import com.bilulo.androidtest04.ui.list.interactor.ListInteractor;

public class ListWorker implements ListContract.WorkerContract {
    public ListInteractor interactor;

    @Override
    public void getStatements(int userId) {
        StatementsService statementsService = new StatementsService();
        statementsService.getStatements(userId, new ResponseListener<StatementsResponse>() {
            @Override
            public void onResponseSuccess(StatementsResponse response) {
                interactor.setStatementsResponse(response, true);
            }

            @Override
            public void onResponseError() {
                interactor.setStatementsResponse(null, false);
            }
        });
    }
}
