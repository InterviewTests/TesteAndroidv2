package com.avanade.testesantander2.homeScreen;

import java.lang.ref.WeakReference;

interface HomeRouterInput{
    void efetuarLogout();
}

public class HomeRouter implements HomeRouterInput{

    public static String TAG = HomeRouter.class.getSimpleName();

    public WeakReference<HomeActivity> activity;
    @Override
    public void efetuarLogout() {

    }
}
