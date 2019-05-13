package br.com.satandertest;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ProgramacaoIII on 28/09/2016.
 */

public class MainApplication extends Application {

    public static SharedPreferences mSharedPreferences;
    public static SharedPreferences.Editor mEditorPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/avenir.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        mSharedPreferences = getApplicationContext().getSharedPreferences("MAIN_PREFERENCES", Context.MODE_PRIVATE);
        mEditorPreferences = mSharedPreferences.edit();

    }
}
