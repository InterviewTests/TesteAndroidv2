package com.test.banktest.loginScreen;


import com.test.banktest.worker.preferenceWorker.PreferenceWorker;
import com.test.banktest.worker.preferenceWorker.PreferenceWorkerInput;
import com.test.banktest.worker.serviceWorker.Listener;
import com.test.banktest.worker.serviceWorker.ServiceWorker;
import com.test.banktest.worker.serviceWorker.ServiceWorkerInput;

interface LoginInteractorInput {
    public void login(LoginRequest request);
    public void getLastUser(LoginRequest aLoginRequest);
}


public class LoginInteractor implements LoginInteractorInput {

    public static String TAG = LoginInteractor.class.getSimpleName();
    public static String KEY_USER = "user";
    public LoginPresenterInput output;
    public LoginWorkerInput aLoginWorkerInput;
    public PreferenceWorkerInput aPreferenceWorker;
    private ServiceWorkerInput aServiceWorker;

    public LoginWorkerInput getLoginWorkerInput() {
        if (aLoginWorkerInput == null) return new LoginWorker();
        return aLoginWorkerInput;
    }

    public ServiceWorkerInput getServiceWorkerInput() {
        if (aServiceWorker == null) return new ServiceWorker();
        return aServiceWorker;
    }

    public PreferenceWorkerInput getPreferenceWorkerInput() {
        if (aPreferenceWorker == null) return new PreferenceWorker();
        return aPreferenceWorker;
    }

    public void setServiceWorker(ServiceWorkerInput aServiceWorker) {
        this.aServiceWorker = aServiceWorker;
    }

    @Override
    public void login(LoginRequest request) {
        aLoginWorkerInput = getLoginWorkerInput();
        boolean isUserValid = aLoginWorkerInput.validateUser(request.user);
        boolean isPasswordValid = aLoginWorkerInput.validatePassword(request.password);
        if(isUserValid && isPasswordValid){
            getServiceWorkerInput().login(request, new Listener<LoginResponse>() {
                @Override
                public void onSuccess(LoginResponse response) {
                    getPreferenceWorkerInput().saveValue(request.context,KEY_USER, request.user);
                    output.presentLoginData(response);
                }

                @Override
                public void onFailure(LoginResponse response) {
                    response.isPasswordValid = true;
                    response.isUserValid = true;
                    output.presentLoginData(response);
                }
            });
        } else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.isUserValid = isUserValid;
            loginResponse.isPasswordValid = isPasswordValid;
            output.presentLoginData(loginResponse);
        }
    }

    @Override
    public void getLastUser(LoginRequest aLoginRequest) {
        aPreferenceWorker = getPreferenceWorkerInput();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.user = aPreferenceWorker.getString(aLoginRequest.context, KEY_USER);
        output.presentLastUser(loginResponse);
    }
}
