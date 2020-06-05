package com.gft.testegft.login.enums;

public enum EnumPasswordErrors {
    NULL("A senha não pode ser nula!"),
    INVALID("A senha deve ter pelo menos uma letra maiúscula, um carácter especial e um carácter alfanumérico!");

    public String desc;

    EnumPasswordErrors(String desc) {
        this.desc = desc;
    }
}
