package com.example.fabio.bankapp.classes;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class Mascara {
    public static String RemoverMascara(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }

    public static TextWatcher InserirMascara(final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";
            String mask = "";
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                String str = Mascara.RemoverMascara(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                if (ediTxt.length()<= 14 && str.matches("[0-9]*")){
                    mask="###.###.###-##";
                    int i = 0;
                    for (char m : mask.toCharArray()) {
                        if (m != '#' && str.length() > old.length()) {
                            mascara += m;
                            continue;
                        }
                        try {
                            mascara += str.charAt(i);
                        } catch (Exception e) {
                            break;
                        }
                        i++;
                    }
                }else {
                    mascara = s.toString();
                }

                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        };
    }
}
