package com.bilulo.androidtest04.ui.list.interactor;

import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.data.model.response.StatementsResponse;
import com.bilulo.androidtest04.ui.list.contract.ListContract;
import com.bilulo.androidtest04.ui.list.worker.ListWorker;

public class ListInteractor implements ListContract.InteractorContract {
    public ListContract.PresenterContract presenter;
    public ListWorker worker;
    private UserAccountModel mUserAccountModel;

    @Override
    public void fetchAndLoadData(UserAccountModel userAccountModel) {
        mUserAccountModel = userAccountModel;
        if (userAccountModel != null && userAccountModel.getUserId() != null) {
            worker.getStatements(userAccountModel.getUserId());
        }
    }

    @Override
    public void setStatementsResponse(StatementsResponse response, boolean isSuccessful) {
        presenter.setData(mUserAccountModel, response, isSuccessful);
    }
}
