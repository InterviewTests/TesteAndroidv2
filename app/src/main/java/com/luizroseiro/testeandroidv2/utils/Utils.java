package com.luizroseiro.testeandroidv2.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.Toast;

import com.luizroseiro.testeandroidv2.MainActivity;
import com.luizroseiro.testeandroidv2.R;

import java.util.regex.Pattern;

public class Utils {

    public static void replaceFragment(Fragment fragment, @Nullable Bundle bundle) {
        if (bundle != null)
            fragment.setArguments(bundle);

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
        return Pattern.matches("^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[@$!#%*?&]).*$", password);
    }

    public static String formatAccount(String account) {
        if (account.length() == 9) {
            return account.substring(0, 2).concat(".").concat(account.substring(2, 8)).concat("-")
                    .concat(account.substring(8));
        }
        else
            return account;
    }

    public static void enableButton(Button button) {
        button.setEnabled(true);
        button.setAlpha(1f);
    }

    public static void disableButton(Button button) {
        button.setEnabled(false);
        button.setAlpha(0.5f);
    }

}
