package com.test.banktest.homeScreen;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.test.banktest.R;


interface HomeActivityInput {
    public void displayHomeData(HomeViewModel viewModel);
}


public class HomeActivity extends AppCompatActivity
        implements HomeActivityInput {

    public static String TAG = HomeActivity.class.getSimpleName();
    HomeInteractorInput output;
    HomeRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //do the setup

        HomeConfigurator.INSTANCE.configure(this);
        HomeRequest aHomeRequest = new HomeRequest();
        //populate the request


        output.fetchHomeData(aHomeRequest);
        // Do other work
    }


    @Override
    public void displayHomeData(HomeViewModel viewModel) {
        Log.e(TAG, "displayHomeData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data
    }
}
