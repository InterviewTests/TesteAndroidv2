package br.com.dpassos.bankandroid.infra;

import android.app.Application;

public class ProjectApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PersistenceContext.setContext(this);
    }
}
