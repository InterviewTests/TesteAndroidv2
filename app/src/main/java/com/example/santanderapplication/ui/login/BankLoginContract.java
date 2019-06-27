package com.example.santanderapplication.ui.login;

public class BankLoginContract {
    interface View{
        void showMessage(String error);
    }

    interface Presenter{
        void validateUser(String user , String password);
    }
}
