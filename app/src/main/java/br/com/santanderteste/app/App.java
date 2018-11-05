package br.com.santanderteste.app;

import android.app.Application;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class App extends Application {

    private App app;

    /**
     * App Singleton
     * @return instance of App
     */
    public App getInstance() {
        if (app == null) {
            this.app = new App();
            return app;
        } else {
            return app;
        }
    }

}
