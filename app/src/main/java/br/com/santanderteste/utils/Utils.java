package br.com.santanderteste.utils;

import android.content.Context;
import android.util.Patterns;

import java.text.NumberFormat;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import br.com.santanderteste.R;

/**
 * Utils.java - Utility class
 *
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class Utils {

    /**
     * Checks whether a cpf is valid or not
     *
     * @param s the cpf
     * @return true if valid, false otherwise
     */
    public static boolean isValidCpf(String s) {
        return Cpf.isValid(s);
    }

    public static boolean isValidEmail(String e) {
        return Patterns.EMAIL_ADDRESS.matcher(e).matches();
    }

    /**
     * Method to format number into Brazilian currency
     */
    public static String numberFormat(double number, boolean currency, Context context) {
        Locale locale = new Locale(context.getString(R.string.pt), context.getString(R.string.country));
        NumberFormat format = NumberFormat.getInstance(locale);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);

        String numberFormatted = format.format(number);

        if (currency) {
            numberFormatted = Const.BRL_CURRENCY + format.format(number);
        }

        return numberFormatted;
    }

    /**
     * formats a string based on the pattern provided
     *
     * @param s
     * @param mask
     * @return
     */
    public static String formatStringWithMask(String s, String mask) {

        StringBuilder builder = new StringBuilder();

        int i = 0;
        int j = 0;
        while (i < mask.length()) {

            if (notSymbol(mask.charAt(i))) {
                builder.append(s.charAt(j));
                i++;
                j++;
            } else {
                builder.append(mask.charAt(i));
                i++;
            }

        }

        return builder.toString();
    }

    public static boolean notSymbol(char c) {
        if (c != '/' && c != '.' && c != '-' && c != ' ') {
            return true;
        }
        return false;
    }

    /**
     * Used to encrypt data
     *
     * @param raw
     * @param clear
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, Const.AES);
        Cipher cipher = Cipher.getInstance(Const.AES);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    /**
     * Used to decrypt data
     *
     * @param raw
     * @param encrypted
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, Const.AES);
        Cipher cipher = Cipher.getInstance(Const.AES);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

}
