package com.gft.testegft.login.enums;

public enum EnumUserErrors {
    NULL("O usuário não pode ser nulo!"),
    INVALID("O usuário deve ser um e-mail ou um CPF válido!");

    public String desc;

    EnumUserErrors(String desc) {
        this.desc = desc;
    }
}
