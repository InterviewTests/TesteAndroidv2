package br.com.satandertest.utils;

/**
 * Created by ProgramacaoIII on 30/09/2016.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.regex.Pattern;


public class Utilities {

    public static void runOnUiThread(Runnable task) {
        new Handler(Looper.getMainLooper()).post(task);
    }

    public static void runOnUiThreadDelayed(Runnable task, int milissegundos) {
        new Handler(Looper.getMainLooper()).postDelayed(task, milissegundos);
    }

    public static String checkString(String input) {
        String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
        char currentCharacter;
        boolean numberPresent = false;
        boolean upperCasePresent = false;
        boolean lowerCasePresent = false;
        boolean specialCharacterPresent = false;

        for (int i = 0; i < input.length(); i++) {
            currentCharacter = input.charAt(i);
            if (Character.isDigit(currentCharacter)) {
                numberPresent = true;
            } else if (Character.isUpperCase(currentCharacter)) {
                upperCasePresent = true;
            } else if (specialChars.contains(String.valueOf(currentCharacter))) {
                specialCharacterPresent = true;
            }
        }

        if (!numberPresent) {
            return "A senha deve possuir um número";
        }

        if (!upperCasePresent) {
            return "A senha deve possuir uma letra maiúscula";
        }

        if (!specialCharacterPresent) {
            return "A senha deve possuir um caracter especial";
        }

        return "";
    }

    public static String formatAgency(String agency) {

        if (agency.length() < 9) {
            return agency;
        } else {
            return agency.substring(0, 2)
                    + "."
                    + agency.substring(2, 8)
                    + "-"
                    + agency.substring(8);
        }

    }

    public static void setCurrencyText(TextView textView, Double value) {

        NumberFormat format = NumberFormat.getCurrencyInstance();
        textView.setText(format.format(value));

    }

    public static void setCurrencyText(TextView textView, String value) {

        value = value.replaceAll(Pattern.quote("."), "");
        Double d = Double.parseDouble(value);
        NumberFormat format = NumberFormat.getCurrencyInstance();
        textView.setText(format.format(d));

    }

    public static String formatDatePt(String date) {

        String[] splittedDate = date.split("-");

        return splittedDate[2] + "/" + splittedDate[1] + splittedDate[0];
    }

    /**
     * Recebe pixels, retorna density Pixels
     *
     * @param context contexto
     * @param px      pixels
     * @return quantidade em density pixels
     */
    public static int pixelsToDensityPixels(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((px / displayMetrics.density) + 0.5);
    }

    /**
     * Recebe density pixels, retorna pixels
     *
     * @param context contexto
     * @param dp      density pixels
     * @return quantidade em pixels
     */
    public static int densityPixelsToPixels(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

}
