package com.bankapp.loginScreen;



import java.lang.ref.WeakReference;

interface LoginPresenterInput {

}


public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();
    public WeakReference<LoginActivityInput> output;


}
