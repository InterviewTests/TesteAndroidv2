package com.example.rossinyamaral.bank.statementsScreen;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

interface StatementsPresenterInput {
    public void presentStatementsData(StatementsResponse response);
}


public class StatementsPresenter implements StatementsPresenterInput {

    public static String TAG = StatementsPresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<StatementsActivityInput> output;


    @Override
    public void presentStatementsData(StatementsResponse response) {
        // Log.e(TAG, "presentStatementsData() called with: response = [" + response + "]");
        //Do your decoration or filtering here
        StatementsViewModel viewModel = new StatementsViewModel();
        viewModel.statements = response.statements;
        output.get().displayStatementsData(viewModel);

    }


}
