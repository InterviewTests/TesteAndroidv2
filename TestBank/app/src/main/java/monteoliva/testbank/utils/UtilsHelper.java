package monteoliva.testbank.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.widget.FrameLayout;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class UtilsHelper {
    /**
     * Method to show SnackBar
     *
     * @param view
     * @param message
     */
    public static void showSnackBar(FrameLayout view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("OK", null)
                .show();
    }

    /**
     * Method to validate CPF
     *
     * @param CPF
     * @return
     */
    public static boolean isValidCPF(@NonNull String CPF) {
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")) {
            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm   = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num  = CPF.charAt(i) - 48;
                sm   = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            }
            else {
                dig10 = (char) (r + 48);
            }

            sm   = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num  = CPF.charAt(i) - 48;
                sm   = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            }
            else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            }
            else {
                return (false);
            }
        }
        catch (Exception error) {
            return (false);
        }
    }

    /**
     * Method to verify password
     *
     * @param password
     * @return
     */
    public static boolean verifyPassword(String password) {
        boolean hasSpecial = false;
        boolean hasUpper   = false;
        boolean hasDigit   = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) { hasUpper = true; }
            if (Character.isDigit(c))     { hasDigit = true; }
        }

        // search for special characters
        if (Pattern.compile("[^a-zA-Z0-9]").matcher(password).find()) {
            hasSpecial = true;
        }

        if (!hasSpecial || !hasDigit || !hasUpper) {
            return false;
        }

        return true;
    }

    /**
     * Method to format date
     *
     * @param date
     * @return
     */
    public static String formatDate(String date) {
        // seta a Formatacao
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        String[] separ = date.split("-");
        int day        = Integer.valueOf(separ[2]);
        int month      = Integer.valueOf(separ[1]);
        int year       = Integer.valueOf(separ[0]);

        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month, day);

        return sdf.format(calendar.getTime());
    }

    public static void saveConfig(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences("config", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getConfig(Context context, String key) {
        String retorno = "";

        try {
            retorno = (context.getSharedPreferences("config", Context.MODE_PRIVATE)).getString(key, "");
        }
        catch (Exception ex) {}

        // retorna
        return retorno;
    }
}