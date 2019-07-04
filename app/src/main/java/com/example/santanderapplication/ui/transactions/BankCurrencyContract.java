package com.example.santanderapplication.ui.transactions;

import com.example.santanderapplication.data.model.StatementsResponseModel;

public class BankCurrencyContract {

    interface StatementView {
        void showMessage(String error);

        void loadList(StatementsResponseModel statementsResponseModel);
    }

    interface StatementPresenter {
        void eatingCurrency(int id);
    }
}
