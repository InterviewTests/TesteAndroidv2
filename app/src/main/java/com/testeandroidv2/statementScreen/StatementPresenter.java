package com.testeandroidv2.statementScreen;

import java.lang.ref.WeakReference;

interface StatementPresenterInput {
    public void presentLoginData(StatementResponse response);
}


public class StatementPresenter implements StatementPresenterInput {

    public static String TAG = StatementPresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<StatementActivityInput> output;


    @Override
    public void presentLoginData(StatementResponse response) {
        // Log.e(TAG, "presentLoginData() called with: response = [" + response + "]");
        //Do your decoration or filtering here

        if(response.user != null) {
            StatementViewModel statementViewModel = getLoginViewModel(response.user);
            output.get().displayLoginData(statementViewModel);
        }
    }

    private StatementViewModel getLoginViewModel(StatementModel statementModel){
        StatementViewModel statementViewModel;

        statementViewModel = new StatementViewModel();
        statementViewModel.name = statementModel.name;
        statementViewModel.agency = statementModel.agency;
        statementViewModel.bankAccount = statementModel.bankAccount;
        statementViewModel.balance = statementModel.balance;

        return statementViewModel;
    }


}
