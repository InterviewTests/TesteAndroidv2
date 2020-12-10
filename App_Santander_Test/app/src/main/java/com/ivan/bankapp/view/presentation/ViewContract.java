package com.ivan.bankapp.view.presentation;

interface ViewContract {
    interface IPresenter {
        void onHomeLoaded();
        void onLogin();
    }

    interface IView {
        void getStatements(String title, String desc, String data, Double value);
        void login(Integer id, String name, String account, String agency, Double value);

    }
}