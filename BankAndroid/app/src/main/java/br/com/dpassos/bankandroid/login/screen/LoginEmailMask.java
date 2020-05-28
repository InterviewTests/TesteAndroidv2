package br.com.dpassos.bankandroid.login.screen;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

class LoginEmailMask implements TextWatcher {

    private final String maskCPF = "###.###.###-##";

    private  EditText input;
    private boolean ignore;

    public void setInput(EditText input) {
        this.input = input;
        input.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) { }

    @Override
    public void afterTextChanged(Editable s) {
        if(ignore) {
            ignore = false;
            return;
        }
        if(s.length() == 0) {
            input.setInputType(EditorInfo.TYPE_CLASS_TEXT);
            return;
        }

        if(s.toString().matches("[0-9]{3,}.{1,}")) {

            String str = s.toString().replaceAll("[^0-9]*", "");
            String mascara = "";
            for (char m : maskCPF.toCharArray()) {
                if(!str.isEmpty()) {
                    if(m == '#') {
                        String first = ""+ str.charAt(0);
                        mascara += str.charAt(0);
                        str = str.replaceFirst(first, "");
                    }else{
                        mascara += m;
                    }
                }else{
                    break;
                }
            }
            ignore = true;
            s.clear();
            ignore = true;
            s.append(mascara);
            input.setSelection(mascara.length());
        }
    }
}