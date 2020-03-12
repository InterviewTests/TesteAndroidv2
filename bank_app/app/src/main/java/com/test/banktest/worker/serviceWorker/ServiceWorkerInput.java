package com.test.banktest.worker.serviceWorker;

import com.test.banktest.homeScreen.HomeRequest;
import com.test.banktest.homeScreen.HomeResponse;
import com.test.banktest.loginScreen.LoginRequest;
import com.test.banktest.loginScreen.LoginResponse;

public interface ServiceWorkerInput {

    void login(LoginRequest loginRequest, Listener<LoginResponse> listener);
    void getStatements(HomeRequest request, Listener<HomeResponse> homeResponseListener);
}
