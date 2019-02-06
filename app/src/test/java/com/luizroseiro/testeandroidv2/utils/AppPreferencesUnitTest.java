package com.luizroseiro.testeandroidv2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.luizroseiro.testeandroidv2.BuildConfig;
import com.luizroseiro.testeandroidv2.MainActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_LOGGED_IN;

@RunWith(RobolectricTestRunner.class)
public class AppPreferencesUnitTest {

    private SharedPreferences sharedPreferences;

    @Before
    public void before() {
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).get();
        sharedPreferences = mainActivity.getSharedPreferences(BuildConfig.APPLICATION_ID,
                Context.MODE_PRIVATE);
    }

    @Test
    public void setUserLoggedInStored() {
        sharedPreferences.edit().putBoolean(PREF_LOGGED_IN, true).apply();

        boolean loggedIn = sharedPreferences.getBoolean(PREF_LOGGED_IN, false);
        Assert.assertTrue(loggedIn);
    }

    @After
    public void after() {
        sharedPreferences.edit().clear().apply();
    }

}