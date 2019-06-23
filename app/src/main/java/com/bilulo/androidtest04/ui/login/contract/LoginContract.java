package com.bilulo.androidtest04.ui.login.contract;

public interface LoginContract {
    interface ActivityContract{
    }

    interface InteractorContract{
        void performLogin(String user, String password);
    }

    interface PresenterContract{

    }
}
