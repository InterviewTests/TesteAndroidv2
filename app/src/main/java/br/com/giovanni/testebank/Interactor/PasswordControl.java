package br.com.giovanni.testebank.Interactor;

import java.util.regex.Pattern;

public class PasswordControl {

    private String getPassword;

    public PasswordControl (String getPassword){
        this.getPassword = getPassword;
    }

    public static final Pattern PASSWORD_PATTER = Pattern.compile
            ("^" +
            "(?=.*[0-9])" +         //verifica se tem pelomenos 1 digito numerico
            "(?=.*[a-z])" +         // verifica se tem pelomenos 1 letra minuscula
            "(?=.*[A-Z])" +         // verifica se tem pelomenos 1 letra maiuscola
            "(?=.*[@#$%^&+=_])" +    // verifica se tem pelomenos 1 caractere especial
            "(?=\\S+$)" +           // verifica se nao tem espaco em branco
            "$");

    public boolean passwordValidation (){
        if (getPassword.isEmpty()){
            return false; // false
        } else if (!PASSWORD_PATTER.matcher(getPassword).matches()){
            System.out.println("Falha aqui");
            return false; // original é false
        } else {
            return true;
        }
    }
}
