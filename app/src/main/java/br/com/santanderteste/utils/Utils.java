package br.com.santanderteste.utils;

import android.util.Patterns;

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

}
