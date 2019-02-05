package com.luizroseiro.testeandroidv2.utils;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.luizroseiro.testeandroidv2.MainActivity;
import com.luizroseiro.testeandroidv2.R;

import java.util.regex.Pattern;

public class Utils {

    public static void replaceFragment(Fragment fragment) {
        MainActivity mainActivity = (MainActivity) MainActivity.getContext();
        mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.cl_main, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public static void createToast(String text) {
        Toast.makeText(MainActivity.getContext(), text, Toast.LENGTH_LONG).show();
    }

    public static boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!#%*?&])$", password);
    }

}
