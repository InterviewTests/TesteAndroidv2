package com.test.banktest.homeScreen;

import com.test.banktest.worker.serviceWorker.Listener;
import com.test.banktest.worker.serviceWorker.ServiceWorker;
import com.test.banktest.worker.serviceWorker.ServiceWorkerInput;

interface HomeInteractorInput {
    public void fetchHomeData(HomeRequest request);
    public void logout();
}


public class HomeInteractor implements HomeInteractorInput {

    public static String TAG = HomeInteractor.class.getSimpleName();
    public HomePresenterInput output;
    public HomeWorkerInput aHomeWorkerInput;
    private ServiceWorkerInput aServiceWorker;

    public HomeWorkerInput getHomeWorkerInput() {
        if (aHomeWorkerInput == null) return new HomeWorker();
        return aHomeWorkerInput;
    }

    public ServiceWorkerInput getServiceWorkerInput() {
        if (aServiceWorker == null) return new ServiceWorker();
        return aServiceWorker;
    }

    public void setHomeWorkerInput(HomeWorkerInput aHomeWorkerInput) {
        this.aHomeWorkerInput = aHomeWorkerInput;
    }

    @Override
    public void fetchHomeData(HomeRequest request) {
        aHomeWorkerInput = getHomeWorkerInput();
        getServiceWorkerInput().getStatements(request,new Listener<HomeResponse>(){

            @Override
            public void onSuccess(HomeResponse response) {
                response.user = request.user;
                output.presentHomeData(response);
            }

            @Override
            public void onFailure(HomeResponse response) {
                output.presentHomeData(response);
            }
        });
    }

    @Override
    public void logout() {
        output.presentLogout();
    }
}
