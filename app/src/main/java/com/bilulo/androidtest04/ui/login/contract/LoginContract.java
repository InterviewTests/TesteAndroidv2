package com.bilulo.androidtest04.ui.login.contract;

import com.bilulo.androidtest04.data.model.response.LoginResponse;

public interface LoginContract {
    public interface ActivityContract{
        void loginSucessful();
        void loginError();
    }

    interface InteractorContract{
        void performLogin(String user, String password);
        void setLoginResponse(LoginResponse response, boolean isSucessful);
    }

    interface PresenterContract{
        void setLoginResponse(LoginResponse response, boolean isSucessful);
    }

    interface WorkerContract{
        void performLogin(String user, String password);
    }

}
