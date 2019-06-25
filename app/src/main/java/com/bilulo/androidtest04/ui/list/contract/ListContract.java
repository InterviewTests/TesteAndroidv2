package com.bilulo.androidtest04.ui.list.contract;

import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.data.model.response.StatementsResponse;
import com.bilulo.androidtest04.ui.list.view.adapter.StatementsAdapter;

public interface ListContract {
    interface ActivityContract{
        void loadUserAccountData(String name, String account, String balance);
        void loadStatements(StatementsAdapter adapter);
        void displayError();
    }

    interface InteractorContract{
        void fetchAndLoadData(UserAccountModel userAccountModel);
        void setStatementsResponse(StatementsResponse response, boolean isSuccessful);
    }

    interface PresenterContract{
        void setData(UserAccountModel userAccountModel, StatementsResponse response, boolean isSuccessful);
    }

    interface WorkerContract{
        void getStatements(int userId);
    }

    interface RouterContract {
    }
}
