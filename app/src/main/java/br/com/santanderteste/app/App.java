package br.com.santanderteste.app;

import android.app.Application;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class App extends Application {

    private static App app;

    /**
     * App Singleton
     *
     * @return instance of App
     */
    public static App getInstance() {
        if (app == null) {
            app = new App();
            return app;
        } else {
            return app;
        }
    }

}
