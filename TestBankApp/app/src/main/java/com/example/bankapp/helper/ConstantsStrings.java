package com.example.bankapp.helper;

public class ConstantsStrings {

    public static final String ERROR_PASSWORD = "Senha não atende os requisitos";
    public static final String ERROR_FIELD_USER_EMPTY = "Preencha o campo user";
    public static final String ERROR_FIELD_PASSWORD_EMPTY = "Preencha o campo password";
    public static final String ERROR_EMAIL_CPF = "Preencha o campo user com um email ou cpf válidos";
    public static final String ERROR_INTERNET = "Verifique suas conexões com a internet";

    //Regex
    public static final String REGEX_EMAIL_CPF = ".+@.+\\..+|[0-9]{11}";
    public static final String REGEX_UPPER_CHARACTERS = "[A-Z]";
    public static final String REGEX_NUMBER_CHARACTERS = "[0-9]";
    public static final String REGEX_SPECIAL_CHARACTERS = "[$&+,:;=?@#|'<>.^*()%!-]";


}
