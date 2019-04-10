package com.desafiosantander.DashBoard;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

interface DashBoardPresenterInput {
    public void presentDashBoardData(DashBoardResponse response);
    public void setStatmentList(List<Statement> statement);
}


public class DashBoardPresenter implements DashBoardPresenterInput {

    public static String TAG = DashBoardPresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<DashBoardActivityInput> output;


    @Override
    public void presentDashBoardData(DashBoardResponse response) {
        // Log.e(TAG, "presentDashBoardData() called with: response = [" + response + "]");
        //Do your decoration or filtering here

    }

    @Override
    public void setStatmentList(List<Statement> statement) {
        DashBoardViewModel dashBoardViewModel = new DashBoardViewModel();
        dashBoardViewModel.statementList = statement;

        output.get().loadList(dashBoardViewModel);
    }


}
