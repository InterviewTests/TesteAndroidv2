package com.bilulo.androidtest04.components;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

public class UserEditText extends android.support.v7.widget.AppCompatEditText {
    private boolean mDelete;

    public UserEditText(Context context) {
        super(context);
        initialize();
    }

    public UserEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public UserEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        this.setText("");

        this.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String cpf = s.toString();
                String cpfAux = convertToOnlyNumberString(cpf);
                int length = cpf.length();
                if (length == 0)
                    return;
                if (!cpfAux.matches("^[0-9]+$")) {
                    return;
                }
                if (length > 14) {
                    UserEditText.this.setText(cpf.substring(0, 14));
                    return;
                }

                UserEditText.this.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN)
                            if (keyCode == event.KEYCODE_DEL) {
                                mDelete = true;
                            } else {
                                mDelete = false;
                            }
                        else {
                            mDelete = false;
                        }
                        return false;
                    }
                });

                if (before == 0) {
                    UserEditText.this.removeTextChangedListener(this);
                    cpfAux = setCPFMask(cpfAux);
                    UserEditText.this.setText(cpfAux);
                    UserEditText.this.addTextChangedListener(this);
                }

                if (mDelete) {
                    UserEditText.this.setSelection(UserEditText.this.getSelectionStart());
                } else {
                    UserEditText.this.setSelection(UserEditText.this.getText().length());
                }

            }
        });
    }

    private String setCPFMask(String cpf) {
        if (cpf.length() >= 3 && cpf.length() < 6) {
            return cpf.substring(0, 3) + "." + cpf.substring(3);
        } else if (cpf.length() >= 6 && cpf.length() < 9) {
            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6);
        }  else if (cpf.length() >= 9 && cpf.length() < 11) {
            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
        } else if (cpf.length() == 11) {
            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        } else {
            return cpf;
        }
    }

    private String convertToOnlyNumberString(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            Character digit = string.charAt(i);
            if (digit >= '0' && digit <= '9')
                result.append(digit);
        }
        return result.toString();
    }
}
