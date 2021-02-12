package com.bank.testeandroidv2.loginScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.bank.testeandroidv2.statementScreen.StatementActivity;
import com.bank.testeandroidv2.statementScreen.StatementHeaderModel;

import java.lang.ref.WeakReference;


interface LoginRouterInput {
    Intent navigateToSomeWhere(int position);
//    void passDataToNextScene(int position, Intent intent);
    void passDataToNextScene(UserAccount uc);
}

public class LoginRouter implements LoginRouterInput {

    public static String TAG = LoginRouter.class.getSimpleName();
    public WeakReference<LoginActivity> activity;

    @Override
    public void passDataToNextScene(UserAccount uc) {
        Intent intent = new Intent(activity.get(), StatementActivity.class);
        StatementHeaderModel statementHeaderModel = new  StatementHeaderModel(uc.userId, uc.name, uc.bankAccount, uc.agency, uc.balance);
        intent.putExtra("statementHeaderModel", statementHeaderModel);
        Bundle bundle = new Bundle();
        bundle.putParcelable("shm_parcel",(Parcelable) statementHeaderModel);
        intent.putExtra("shm_extra", bundle);
        activity.get().startActivity(intent);
    }


    @NonNull
    @Override
    public Intent navigateToSomeWhere(int position) {
        //Based on the position or someother data decide what is the next scene
        Intent intent = new Intent(activity.get(),StatementActivity.class);
        return intent;
    }
//
//    @Override
//    public void passDataToNextScene(int position, Intent intent) {
//        //Based on the position or someother data decide the data for the next scene
//        // LoginModel flight = activity.get().listOfSomething.get(position);
//        // intent.putExtra("flight",flight);
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        // Log.e(TAG, "onItemClick() called with: parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
//        Intent intent = navigateToSomeWhere(position);
//        passDataToNextScene(position, intent);
//        activity.get().startActivity(intent);
//    }


}
