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

    public static String formatAgency(String agency) {
        if (agency.length() == 9) {
            return agency.substring(0, 2).concat(".").concat(agency.substring(2, 8)).concat("-")
                    .concat(agency.substring(8));
        }
        else
            return agency;
    }

    public static String formatDate(String date) {
        if (Pattern.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", date)) {
            String year = date.substring(0, date.indexOf("-"));
            String month = date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"));
            String day = date.substring(date.lastIndexOf("-") + 1);

            return day.concat("/").concat(month).concat("/").concat(year);
        }
        else
            return date;
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
