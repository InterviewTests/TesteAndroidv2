package com.luizroseiro.testeandroidv2.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.Toast;

import com.luizroseiro.testeandroidv2.views.activities.MainActivity;

import java.util.regex.Pattern;

public class Utils {

    public static void replaceFragment(int container, Fragment fragment) {
        FragmentManager fragmentManager = MainActivity.getMainActivity()
                .getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static void enableButton(Button button) {
        button.setAlpha(1.0f);
        button.setClickable(true);
    }

    public static void disableButton(Button button) {
        button.setAlpha(0.5f);
        button.setClickable(false);
    }

    public static boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).*$", password);
    }

    public static void createToast(String text) {
        Toast.makeText(MainActivity.getMainActivity(), text, Toast.LENGTH_LONG).show();
    }

}
