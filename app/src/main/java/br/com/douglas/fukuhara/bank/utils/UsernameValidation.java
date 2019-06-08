package br.com.douglas.fukuhara.bank.utils;

import androidx.annotation.IntDef;

public class UsernameValidation {

    @IntDef({VALID_CPF, VALID_EMAIL, INVALID_CPF, INVALID_EMAIL_CPF})

    public @interface Type { }

    public static final int VALID_CPF = 0;
    public static final int VALID_EMAIL = 1;
    public static final int INVALID_CPF = 2;
    public static final int INVALID_EMAIL_CPF = 3;
}
