//
package com.bank.testeandroidv2.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class MaskEditUtil {
    public static final String FORMAT_CPF = "###.###.###-##";
    public static final String FORMAT_FONE = "(###)####-#####";
    public static final String FORMAT_CEP = "#####-###";
    public static final String FORMAT_DATE = "##/##/####";
    public static final String FORMAT_HOUR = "##:##";
    public static final String FORMAT_PIN = "####";

    /**
     * Método que deve ser chamado para realizar a formatação
     *
     * @param ediTxt
     * @param mask
     * @return
     */
    public static TextWatcher mask(final EditText ediTxt, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {}

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = MaskEditUtil.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
        };
    }

    /**
     * Método que deve ser chamado para realizar a formatação
     *
     * @param ediTxt
     * @return
     */
    public static TextWatcher mask_hour(final EditText ediTxt) {
        return new TextWatcher() {
            String mask = FORMAT_HOUR;
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {}

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                String str = MaskEditUtil.unmask(s.toString());
                str = validateHour(str);
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
                ediTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            if(ediTxt.getText().toString().length() == 1){
                                String s = "00:0" + ediTxt.getText().toString();
                                ediTxt.setText(s);
                            }
                            else if(ediTxt.getText().toString().length() == 2){
                                String s = "00:" + (ediTxt.getText().toString()).substring(0,2);
                                ediTxt.setText(s);
                            }
                            else if(ediTxt.getText().toString().length() == 3){
                                String s = "00:" + (ediTxt.getText().toString()).substring(0,2);
                                ediTxt.setText(s);
                            }
                            else if(ediTxt.getText().toString().length() == 4){
                                String s = ediTxt.getText().toString() + "0";
                                ediTxt.setText(s);
                            }
                        }
                    }
                });
            }
        };
    }

    public static String unmask(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[:]", "").replaceAll("[)]", "");
    }

    private static String validateHour(String hour) {
        String newHour = "";
        if(hour.length() == 1){
            int number1 = Integer.parseInt(hour);
            if(number1 <= 2)
                newHour = hour;
            else
                newHour = "0"+hour;
        }
        if(hour.length() == 2){
            int number1 = Integer.parseInt(hour.substring(0,1));
            String s2 = hour.substring(1,2);
            int number2 = Integer.parseInt(s2);
            if (number1 <= 1)
                newHour = hour;
            else {
                if (number2 <= 3)
                    newHour = hour;
                else
                    newHour = String.valueOf(number1);
            }
        }
        if(hour.length() == 3){
            int number3 = Integer.parseInt(hour.substring(2,3));
            if(number3 <= 5)
                newHour = hour;
            else
                return hour.substring(0,2);
        }
        if(hour.length() == 4)
            newHour = hour;
        return newHour;
    }

}
