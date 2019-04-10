package com.desafiosantander.Home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.desafiosantander.DashBoard.DashBoardActivity;
import com.desafiosantander.LoggedUser;

import java.lang.ref.WeakReference;


interface HomeRouterInput {
    public Intent navigateToSomeWhere(int position);
    public void login(LoggedUser loggedUser);

    public void passDataToNextScene(int position, Intent intent);
}

public class HomeRouter implements HomeRouterInput, AdapterView.OnItemClickListener {

    public static String TAG = HomeRouter.class.getSimpleName();
    public WeakReference<HomeActivity> activity;


    @NonNull
    @Override
    public Intent navigateToSomeWhere(int position) {
        //Based on the position or someother data decide what is the next scene
        //Intent intent = new Intent(activity.get(),NextActivity.class);
        //return intent;
        return null;
    }

    @Override
    public void login(LoggedUser loggedUser) {
        Log.d("Router","OPEN  DASH");

        Intent intent = new Intent(activity.get(), DashBoardActivity.class);
        intent.putExtra("userName",loggedUser.getName());
        intent.putExtra("account",loggedUser.getBankAccount());
        intent.putExtra("agency",loggedUser.getAgency());
        intent.putExtra("balance",loggedUser.getBalance());
        activity.get().startActivity(intent);
    }

    @Override
    public void passDataToNextScene(int position, Intent intent) {
        //Based on the position or someother data decide the data for the next scene
        // HomeModel flight = activity.get().listOfSomething.get(position);
        // intent.putExtra("flight",flight);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Log.e(TAG, "onItemClick() called with: parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
        Intent intent = navigateToSomeWhere(position);
        passDataToNextScene(position, intent);
        activity.get().startActivity(intent);
    }


}
