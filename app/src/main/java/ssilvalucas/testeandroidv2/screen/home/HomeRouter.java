package ssilvalucas.testeandroidv2.screen.home;

import android.content.Intent;

import java.lang.ref.WeakReference;

import ssilvalucas.testeandroidv2.screen.login.LoginActivity;

interface HomeRouterInterface{
    void doLogout();
}

public class HomeRouter implements HomeRouterInterface {

    public WeakReference<HomeActivity> activity;

    @Override
    public void doLogout() {
        Intent intent = new Intent(activity.get(), LoginActivity.class);
        activity.get().startActivity(intent);
        activity.get().finish();
    }
}
