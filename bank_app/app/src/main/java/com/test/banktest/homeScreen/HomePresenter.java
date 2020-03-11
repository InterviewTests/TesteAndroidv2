package com.test.banktest.homeScreen;

import java.lang.ref.WeakReference;

interface HomePresenterInput {
    public void presentHomeData(HomeResponse response);
}


public class HomePresenter implements HomePresenterInput {

    public static String TAG = HomePresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<HomeActivityInput> output;


    @Override
    public void presentHomeData(HomeResponse response) {
        // Log.e(TAG, "presentHomeData() called with: response = [" + response + "]");
        //Do your decoration or filtering here

    }


}
