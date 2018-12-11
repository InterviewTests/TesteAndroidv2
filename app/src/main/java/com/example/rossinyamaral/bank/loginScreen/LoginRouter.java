package com.example.rossinyamaral.bank.loginScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.rossinyamaral.bank.model.UserAccountModel;
import com.example.rossinyamaral.bank.statementsScreen.StatementsActivity;

import java.lang.ref.WeakReference;


interface LoginRouterInput {
    public Intent navigateToSomeWhere();

    public void passDataToNextScene(Intent intent, UserAccountModel model);

    public void onLoginClick(UserAccountModel model);
}

public class LoginRouter implements LoginRouterInput { //, AdapterView.OnItemClickListener {

    public static String TAG = LoginRouter.class.getSimpleName();
    public WeakReference<LoginActivity> activity;


    @NonNull
    @Override
    public Intent navigateToSomeWhere() {
        //Based on the position or someother data decide what is the next scene
        Intent intent = new Intent(activity.get(), StatementsActivity.class);
        return intent;
//        return null;
    }

    @Override
    public void passDataToNextScene(Intent intent, UserAccountModel model) {
        //Based on the position or someother data decide the data for the next scene
         intent.putExtra("userAccount", model);
    }

    @Override
    public void onLoginClick(UserAccountModel model) {
        if (model != null) {
            Intent intent = navigateToSomeWhere();
            passDataToNextScene(intent, model);
            activity.get().startActivity(intent);
        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        // Log.e(TAG, "onItemClick() called with: parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
//        Intent intent = navigateToSomeWhere(position);
//        passDataToNextScene(position, intent);
//        activity.get().startActivity(intent);
//    }


}
