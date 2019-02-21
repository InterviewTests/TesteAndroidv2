package com.avanade.testesantander2.util;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Pattern;

public abstract class SenhaUtil {

    public static TextWatcher insert(final EditText editText) {

        return new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isValid(s.toString()))
                    editText.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                else
                    editText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    /**
     * Method Regex Pattern para validar PASSWORDs
     *
     * @param senha     String para ser avaliada pelo regex pattern
     * @return boolen   Match do pattern
     *
     * String pattern
     * "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$"
     *
     *
     * Explanation:
     *
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $
     */
    public static boolean isValid(String senha) {
        String emailPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[\\W])(?=\\S+$).{3,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        if (pattern.matcher(senha).matches())
                return true;
        return false;
    }



}
