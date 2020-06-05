package com.bank.testeandroidv2.loginScreen;

import com.bank.testeandroidv2.R;

public enum LoginStatusMessageEnum {
    EMPTY_FIELDS (R.string.validation_error_empty_fields),
    USER_FIELD_EMPTY (R.string.validation_error_user_field_empty),
    PASS_FIELD_EMPTY (R.string.validation_error_password_field_empty),
    INVALID_USER_CPF (R.string.validation_error_invalid_cpf),
    INVALID_USER_EMAIL (R.string.validation_error_invalid_email),
    INVALID_PASSWORD (R.string.validation_error_invalid_password),
    FIELDS_OK (R.string.validation_fields_ok);

    private final int value;

    private LoginStatusMessageEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
