package com.example.rossinyamaral.bank.statementsScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

import java.lang.ref.WeakReference;


interface StatementsRouterInput {
    public Intent navigateToSomeWhere(int position);

    public void passDataToNextScene(int position, Intent intent);
}

public class StatementsRouter implements StatementsRouterInput {

    public static String TAG = StatementsRouter.class.getSimpleName();
    public WeakReference<StatementsActivity> activity;


    @NonNull
    @Override
    public Intent navigateToSomeWhere(int position) {
        //Based on the position or someother data decide what is the next scene
        //Intent intent = new Intent(activity.get(),NextActivity.class);
        return new Intent();
    }

    @Override
    public void passDataToNextScene(int position, Intent intent) {
        //Based on the position or someother data decide the data for the next scene
        // StatementsModel flight = activity.get().listOfSomething.get(position);
        // intent.putExtra("flight",flight);
    }

}
