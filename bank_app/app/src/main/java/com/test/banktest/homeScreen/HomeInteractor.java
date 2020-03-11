package com.test.banktest.homeScreen;

import android.util.Log;

interface HomeInteractorInput {
    public void fetchHomeData(HomeRequest request);
}


public class HomeInteractor implements HomeInteractorInput {

    public static String TAG = HomeInteractor.class.getSimpleName();
    public HomePresenterInput output;
    public HomeWorkerInput aHomeWorkerInput;

    public HomeWorkerInput getHomeWorkerInput() {
        if (aHomeWorkerInput == null) return new HomeWorker();
        return aHomeWorkerInput;
    }

    public void setHomeWorkerInput(HomeWorkerInput aHomeWorkerInput) {
        this.aHomeWorkerInput = aHomeWorkerInput;
    }

    @Override
    public void fetchHomeData(HomeRequest request) {
        aHomeWorkerInput = getHomeWorkerInput();
        HomeResponse HomeResponse = new HomeResponse();
        // Call the workers

        output.presentHomeData(HomeResponse);
    }
}
